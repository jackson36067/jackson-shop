package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.CouponConstant;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.RemoveMemberCouponDTO;
import com.jackson.dto.StoreCouponDTO;
import com.jackson.entity.ShopCoupon;
import com.jackson.entity.ShopMemberCoupon;
import com.jackson.entity.ShopStore;
import com.jackson.exception.CouponNumberNullException;
import com.jackson.exception.HaveCouponException;
import com.jackson.repository.CouponRepository;
import com.jackson.repository.MemberCouponRepository;
import com.jackson.repository.MemberFollowStoreRepository;
import com.jackson.repository.StoreRepository;
import com.jackson.result.Result;
import com.jackson.service.CouponService;
import com.jackson.vo.CouponVO;
import com.jackson.vo.MemberCouponItemVO;
import com.jackson.vo.MemberCouponTypeVO;
import com.jackson.vo.MemberCouponVO;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {
    @Resource
    private CouponRepository couponRepository;
    @Resource
    private MemberCouponRepository memberCouponRepository;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private StoreRepository storeRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MemberFollowStoreRepository memberFollowStoreRepository;

    private static final DefaultRedisScript<Long> COUPON_SCRIPT;

    // 定义使用redis读取lua脚本
    static {
        COUPON_SCRIPT = new DefaultRedisScript<>();
        // 设置脚本返回类型
        COUPON_SCRIPT.setResultType(Long.class);
        // 设置脚本路径, /resource/coupon.lua
        COUPON_SCRIPT.setLocation(new ClassPathResource("coupon.lua"));
    }


    /**
     * 获取用户可以获取的店铺提供的优惠卷列表
     *
     * @param id 店家id
     * @return
     */
    public Result<List<CouponVO>> getUnGetStoreCouponList(Long id) {
        // 获取该店家所有优惠卷
        List<ShopCoupon> shopCouponList = couponRepository.findAllByShopStore_IdAndExpireTimeAfter(id, LocalDateTime.now());
        Long userId = BaseContext.getCurrentId();
        // 移除用户已经领取过的优惠卷, 获取通过优惠卷获取用户信息, 返回不包含该用户的优惠卷
        shopCouponList = shopCouponList.stream()
                .filter(shopCoupon ->
                        memberCouponRepository.existsByUserIdAndCouponId(userId, shopCoupon.getId())
                ).toList();
        // 转换信息为自己想要的
        List<CouponVO> couponVOList = shopCouponList.stream().map(shopCoupon -> BeanUtil.copyProperties(shopCoupon, CouponVO.class)).toList();
        return Result.success(couponVOList);
    }

    /**
     * 领取店铺提供的优惠卷
     *
     * @param shopCouponDTO 店铺id以及优惠卷id
     */
    @Transactional
    public void getStoreCoupon(StoreCouponDTO shopCouponDTO) {
        Long userId = BaseContext.getCurrentId();
        // 执行lua脚本,获取返回结果
        Long result = stringRedisTemplate.execute(COUPON_SCRIPT, Collections.emptyList(), userId.toString(), shopCouponDTO.getCouponId().toString());
        // 结果为1 -> 优惠卷被抢光了
        if (result == 1) {
            throw new CouponNumberNullException(CouponConstant.COUPON_NULL);
        }
        // 结果为2 -> 该用户已经拥有了该优惠卷
        if (result == 2) {
            throw new HaveCouponException(CouponConstant.OWNER_COUPON);
        }
        // 用户拥有条件抢购优惠卷, 通过异步删减库存以及保存用户抢购优惠卷信息
        Map<String, Long> info = new HashMap<>();
        // 封装异步需要的信息
        info.put("userId", userId);
        info.put("couponId", shopCouponDTO.getCouponId());
        info.put("storeId", shopCouponDTO.getStoreId());
        rabbitTemplate.convertAndSend(RabbitMQConstant.COUPON_QUEUE, info);
        // 判读是否关注店铺
        if (shopCouponDTO.getStoreId() != null) {
            Boolean IsFollow = memberFollowStoreRepository.existsByMemberIdAndStoreId(userId, shopCouponDTO.getStoreId());
            // 如果未关注店铺 -> 那么就发送异步关注店铺
            if (!IsFollow) {
                info.remove("couponId");
                rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE_KEY, info);
            }
        }
    }

    /**
     * 获取用户尚未使用的优惠卷
     *
     * @return
     */
    public Result<List<MemberCouponTypeVO>> getMemberCouponList() {
        // 返回结果
        List<MemberCouponTypeVO> result = new ArrayList<>();

        // 获取所有用户可以使用的优惠卷 参数一: 用户id, 参数二: 是否被使用, 参数三: 是否被删除, 参数四: 是否过期
        List<ShopMemberCoupon> shopMemberCouponList = memberCouponRepository.findAllByUserIdAndUseStatusAndDelFlagAndExpireTimeAfter(BaseContext.getCurrentId(), (short) 0, (short) 0, LocalDateTime.now());
        // 遍历shopMemberCouponListGroupByStoreId-key数据得到想要的数据返回
        Map<Boolean, List<ShopMemberCoupon>> collected = shopMemberCouponList.stream().collect(Collectors.groupingBy(shopMemberCoupon -> shopMemberCoupon.getStoreId() != null));

        // 平台提供的优惠卷
        List<ShopMemberCoupon> shopMemberPlatformCoupon = collected.get(false);
        if (shopMemberPlatformCoupon != null && !shopMemberPlatformCoupon.isEmpty()) {
            // 封装平台提供的商品结果
            List<MemberCouponItemVO> memberPlatformCouponItemVoList = shopMemberPlatformCoupon.stream()
                    .map(shopMemberCoupon -> BeanUtil.copyProperties(shopMemberCoupon, MemberCouponItemVO.class))
                    .toList();
            MemberCouponTypeVO<MemberCouponItemVO> memberCouponItemVOMemberCouponTypeVO = new MemberCouponTypeVO<>();
            memberCouponItemVOMemberCouponTypeVO.setMemberCouponList(memberPlatformCouponItemVoList);
            memberCouponItemVOMemberCouponTypeVO.setType("平台");
            result.add(memberCouponItemVOMemberCouponTypeVO);
        }

        // 店铺提供的优惠卷
        List<ShopMemberCoupon> shopMemberStoreCouponemberCouponList = collected.get(true);
        // 通过storeId分组得到每个店铺提供的优惠卷列表
        if (shopMemberStoreCouponemberCouponList != null && !shopMemberStoreCouponemberCouponList.isEmpty()) {
            Map<Long, List<ShopMemberCoupon>> shopMemberCouponListGroupByStoreId = shopMemberStoreCouponemberCouponList.stream().collect(Collectors.groupingBy(ShopMemberCoupon::getStoreId));
            List<MemberCouponVO> memberCouponVOList = shopMemberCouponListGroupByStoreId.keySet().stream().map(storeId -> {
                        MemberCouponVO memberCouponVO = new MemberCouponVO();
                        // 获取shopStore
                        ShopStore shopStore = storeRepository.findById(storeId).get();
                        // 封装店铺相关数据
                        memberCouponVO.setStoreId(shopStore.getId());
                        memberCouponVO.setAvatar(shopStore.getAvatar());
                        memberCouponVO.setName(shopStore.getName());
                        // 设置每个店铺的优惠卷列表
                        List<MemberCouponItemVO> memberStoreCouponItemVOList = shopMemberCouponListGroupByStoreId.get(storeId)
                                .stream()
                                .map(shopMemberCoupon -> BeanUtil.copyProperties(shopMemberCoupon, MemberCouponItemVO.class))
                                .toList();
                        memberCouponVO.setMemberCouponItemVOList(memberStoreCouponItemVOList);
                        return memberCouponVO;
                    })
                    .toList();
            // 封装店铺提供的优惠卷数据
            MemberCouponTypeVO<MemberCouponVO> memberCouponVOMemberCouponTypeVO = new MemberCouponTypeVO<>();
            memberCouponVOMemberCouponTypeVO.setMemberCouponList(memberCouponVOList);
            memberCouponVOMemberCouponTypeVO.setType("店铺");
            result.add(memberCouponVOMemberCouponTypeVO);
        }
        return Result.success(result);
    }

    /**
     * 根据用户优惠卷id集合,珊瑚用户优惠卷,支持多删
     *
     * @param removeMemberCouponDTO 用户优惠卷id
     */
    public void removeAllByMemberCouponIdList(RemoveMemberCouponDTO removeMemberCouponDTO) {
        // 这里不能将优惠卷数据删除, 因为判断用户是否拥有该店铺的优惠卷时需要判断是否有该数据
        // 这里可以将用户优惠卷中的字段delFlag改成删除状态,让数据保留
        memberCouponRepository.updateDelFlagByIds(removeMemberCouponDTO.getIdList(), (short) 1);
    }

    /**
     * 获取用户可领取的平台提供的优惠卷
     *
     * @return
     */
    public Result<List<CouponVO>> getCenterCoupon() {
        // 获取所有平台提供的优惠卷
        List<ShopCoupon> platformCouponList = couponRepository.findAllByTypeAndExpireTimeBefore((short) 1, LocalDateTime.now());
        // 过滤出用户已经获取的优惠卷
        List<CouponVO> platformCouponVoList = platformCouponList
                .stream()
                .filter(platformCoupon ->
                        memberCouponRepository.existsByUserIdAndCouponId(BaseContext.getCurrentId(), platformCoupon.getId())
                )
                .map(shopCoupon -> BeanUtil.copyProperties(shopCoupon, CouponVO.class))
                .toList();
        return Result.success(platformCouponVoList);
    }

    /**
     * 获取店铺可用优惠卷以及可用平台卷
     *
     * @return
     */
    public Result<List<CouponVO>> getMemberCanUseCoupon(List<Long> storeIds) {
        if (BaseContext.getCurrentId() == null) {
            return Result.success(new ArrayList<>());
        }
        List<ShopMemberCoupon> shopMemberCouponList = memberCouponRepository.findAllByStoreIdInAndUserIdAndUseStatusAndDelFlagAndExpireTimeAfter(storeIds, BaseContext.getCurrentId(), (short) 0, (short) 0, LocalDateTime.now());
        List<CouponVO> couponVOList = shopMemberCouponList.
                stream()
                .map(shopMemberCoupon -> BeanUtil.copyProperties(shopMemberCoupon, CouponVO.class))
                .toList();
        return Result.success(couponVOList);
    }
}
