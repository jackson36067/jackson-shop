package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.RemoveMemberCouponDTO;
import com.jackson.dto.StoreCouponDTO;
import com.jackson.entity.ShopCoupon;
import com.jackson.entity.ShopMemberCoupon;
import com.jackson.entity.ShopStore;
import com.jackson.repository.CouponRepository;
import com.jackson.repository.MemberCouponRepository;
import com.jackson.repository.StoreRepository;
import com.jackson.result.Result;
import com.jackson.service.CouponService;
import com.jackson.vo.CouponVO;
import com.jackson.vo.MemberCouponItemVO;
import com.jackson.vo.MemberCouponVO;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 获取店铺提供的优惠卷
     *
     * @param id 店家id
     * @return
     */
    public Result<List<CouponVO>> getUnGetStoreCouponList(Long id) {
        // 获取该店家所有优惠卷
        List<ShopCoupon> shopCouponList = couponRepository.findAllByShopStoreId(id);
        Long userId = BaseContext.getCurrentId();
        // 移除用户已经领取过的优惠卷, 获取通过优惠卷获取用户信息, 返回不包含该用户的优惠卷
        shopCouponList = shopCouponList.stream()
                .filter(shopCoupon ->
                        memberCouponRepository.findByUserIdAndCouponId(shopCoupon.getId(), userId) == null
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
        // 处理优惠卷 -> 保存到用户优惠卷数据库
        ShopCoupon shopCoupon = couponRepository.findById(shopCouponDTO.getCouponId()).get();
        Long userId = BaseContext.getCurrentId();
        ShopMemberCoupon shopMemberCoupon = new ShopMemberCoupon();
        shopMemberCoupon.setDiscount(shopCoupon.getDiscount());
        shopMemberCoupon.setMin(shopCoupon.getMin());
        shopMemberCoupon.setTitle(shopCoupon.getTitle());
        shopMemberCoupon.setUserId(userId);
        shopMemberCoupon.setCouponId(shopCouponDTO.getCouponId());
        shopMemberCoupon.setExpireTime(LocalDateTime.now().plusDays(shopCoupon.getExpireDay()));
        shopMemberCoupon.setUseStatus((short) 0);
        shopMemberCoupon.setStoreId(shopCouponDTO.getStoreId());
        shopMemberCoupon.setDelFlag((short) 0);
        memberCouponRepository.save(shopMemberCoupon);
        // 更新优惠卷数据
        shopCoupon.setReceiveNum(shopCoupon.getReceiveNum() + 1);
        couponRepository.saveAndFlush(shopCoupon);
        // 判读是否关注店铺
        shopMemberCoupon = memberCouponRepository.findByUserIdAndCouponId(userId, shopCouponDTO.getCouponId());
        if (shopMemberCoupon == null) {
            // 处理关注店铺 , 异步处理,使用rabbitmq
            Map<String, Long> info = new HashMap<>();
            info.put("userId", userId);
            info.put("storeId", shopCouponDTO.getStoreId());
            rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE_KEY, info);
        }
    }

    /**
     * 获取用户尚未使用的优惠卷
     *
     * @return
     */
    public Result<List<MemberCouponVO>> getMemberCouponList() {
        // 获取所有用户可以使用的优惠卷 参数一: 用户id, 参数二: 是否被使用, 参数三: 是否被删除, 参数四: 是否过期
        List<ShopMemberCoupon> shopMemberCouponList = memberCouponRepository.findAllByUserIdAndUseStatusAndDelFlagAndExpireTimeAfter(BaseContext.getCurrentId(), (short) 0, (short) 0, LocalDateTime.now());
        // 通过storeId分组得到每个店铺提供的优惠卷列表
        Map<Long, List<ShopMemberCoupon>> shopMemberCouponListGroupByStoreId = shopMemberCouponList.stream().collect(Collectors.groupingBy(ShopMemberCoupon::getStoreId));
        // 遍历shopMemberCouponListGroupByStoreId-key数据得到想要的数据返回
        List<MemberCouponVO> memberCouponVOList = shopMemberCouponListGroupByStoreId.keySet().stream().map(storeId -> {
                    MemberCouponVO memberCouponVO = new MemberCouponVO();
                    // 获取shopStore
                    ShopStore shopStore = storeRepository.findById(storeId).get();
                    // 封装店铺相关数据
                    memberCouponVO.setStoreId(shopStore.getId());
                    memberCouponVO.setAvatar(shopStore.getAvatar());
                    memberCouponVO.setName(shopStore.getName());
                    // 设置每个店铺的优惠卷列表
                    List<MemberCouponItemVO> memberCouponItemVOList = shopMemberCouponListGroupByStoreId.get(storeId)
                            .stream()
                            .map(shopMemberCoupon -> BeanUtil.copyProperties(shopMemberCoupon, MemberCouponItemVO.class))
                            .toList();
                    memberCouponVO.setMemberCouponItemVOList(memberCouponItemVOList);
                    return memberCouponVO;
                })
                .toList();
        return Result.success(memberCouponVOList);
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
}
