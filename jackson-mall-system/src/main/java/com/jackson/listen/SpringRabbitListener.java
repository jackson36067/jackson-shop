package com.jackson.listen;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.BrowseHistoryConstant;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.entity.ShopCoupon;
import com.jackson.entity.ShopMemberBrowseHistory;
import com.jackson.entity.ShopMemberCoupon;
import com.jackson.entity.ShopMemberFollowStore;
import com.jackson.repository.CouponRepository;
import com.jackson.repository.MemberBrowseHistoryRepository;
import com.jackson.repository.MemberCouponRepository;
import com.jackson.repository.MemberFollowStoreRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Slf4j
public class SpringRabbitListener {

    @Resource
    private MemberFollowStoreRepository memberFollowStoreRepository;
    @Resource
    private MemberBrowseHistoryRepository memberBrowseHistoryRepository;
    @Resource
    private CouponRepository couponRepository;
    @Resource
    private MemberCouponRepository shopCouponRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 监听队列shop_queue的信息,将信息添加到数据库中,用户关注店铺信息
     *
     * @param info userId,storeId
     */
    @RabbitListener(queues = RabbitMQConstant.QUEUE_KEY)
    private void listenMemberFollowStore(Map<String, Long> info) {
        Long userId = info.get("userId");
        Long storeId = info.get("storeId");
        ShopMemberFollowStore shopMemberFollowStore = new ShopMemberFollowStore(null, userId, storeId, null);
        memberFollowStoreRepository.save(shopMemberFollowStore);
    }

    @RabbitListener(queues = RabbitMQConstant.BROWSE_QUEUE_KEY)
    private void listenMemberBrowseStore(Map<String, Long> info) {
        Long userId = info.get("memberId");
        Long storeId = info.get("storeId");
        Long goodsId = info.get("goodsId");
        Long commentId = info.get("commentId");
        Long type = info.get("type");
        ShopMemberBrowseHistory shopMemberBrowseHistory = new ShopMemberBrowseHistory();
        shopMemberBrowseHistory.setMemberId(userId);
        if (storeId != null) {
            shopMemberBrowseHistory.setStoreId(storeId);
        }
        if (goodsId != null) {
            shopMemberBrowseHistory.setGoodsId(goodsId);
        }
        if (commentId != null) {
            shopMemberBrowseHistory.setCommentId(commentId);
        }
        shopMemberBrowseHistory.setType(Short.parseShort(type.toString()));
        memberBrowseHistoryRepository.save(shopMemberBrowseHistory);
    }

    @RabbitListener(queues = RabbitMQConstant.COUPON_QUEUE)
    private void listenBuyCoupon(Map<String, Long> info) {
        Long userId = info.get("userId");
        Long couponId = info.get("couponId");
        Long storeId = info.get("storeId");
        // 查看该优惠卷枪是否还有库存
        int i = couponRepository.isCouponHaveNum(couponId);
        if (i == 0) {
            // 判断数据库中是否还有库存,没有了,则回滚redis预知库存的删减
            stringRedisTemplate.opsForValue().increment("coupon:stock:" + couponId);
            // 从redis中删除用户已经领取优惠卷信息
            stringRedisTemplate.opsForSet().remove("member:coupon:" + couponId, userId);
            return;
        }
        ShopCoupon shopCoupon = couponRepository.findById(couponId).get();
        // 更新优惠卷数据
        shopCoupon.setNum(shopCoupon.getNum() - 1);
        shopCoupon.setReceiveNum(shopCoupon.getReceiveNum() + 1);
        couponRepository.saveAndFlush(shopCoupon);
        // 优惠卷数量删减没有问题后添加用户领取优惠卷信息到数据库中
        ShopMemberCoupon shopMemberCoupon = new ShopMemberCoupon();
        shopMemberCoupon.setStoreId(storeId);
        shopMemberCoupon.setDiscount(shopCoupon.getDiscount());
        shopMemberCoupon.setMin(shopCoupon.getMin());
        shopMemberCoupon.setTitle(shopCoupon.getTitle());
        shopMemberCoupon.setCouponId(couponId);
        shopMemberCoupon.setExpireTime(LocalDateTime.now().plusDays(shopCoupon.getExpireDay()));
        shopMemberCoupon.setUserId(userId);
        shopMemberCoupon.setUseStatus((short) 0);
        shopMemberCoupon.setDelFlag((short) 0);
        shopCouponRepository.save(shopMemberCoupon);
    }
}
