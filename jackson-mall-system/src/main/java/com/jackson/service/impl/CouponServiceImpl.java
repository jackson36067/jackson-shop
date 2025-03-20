package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.StoreCouponDTO;
import com.jackson.entity.ShopCoupon;
import com.jackson.entity.ShopMemberCoupon;
import com.jackson.repository.CouponRepository;
import com.jackson.repository.MemberCouponRepository;
import com.jackson.result.Result;
import com.jackson.service.CouponService;
import com.jackson.vo.CouponVO;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponServiceImpl implements CouponService {
    @Resource
    private CouponRepository couponRepository;
    @Resource
    private MemberCouponRepository memberCouponRepository;
    @Resource
    private RabbitTemplate rabbitTemplate;

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
                        !memberCouponRepository.findAllByCouponId(shopCoupon.getId()).stream().map(ShopMemberCoupon::getUserId).toList().contains(userId)
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
        memberCouponRepository.save(shopMemberCoupon);
        // 更新优惠卷数据
        shopCoupon.setReceiveNum(shopCoupon.getReceiveNum() + 1);
        couponRepository.saveAndFlush(shopCoupon);
        // 处理关注店铺 , 异步处理,使用rabbitmq
        Map<String, Long> info = new HashMap<>();
        info.put("userId", userId);
        info.put("storeId", shopCouponDTO.getStoreId());
        rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE_KEY, info);
    }
}
