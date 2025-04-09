package com.jackson.listen;

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

import java.time.LocalDate;
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

    /**
     * 监听browse_queue的消息,将用户的浏览记录保存
     *
     * @param info 用户的浏览信息
     */
    @RabbitListener(queues = RabbitMQConstant.BROWSE_QUEUE_KEY)
    private void listenMemberBrowseStore(Map<String, Long> info) {
        Long userId = info.get("memberId");
        Long storeId = info.get("storeId");
        Long goodsId = info.get("goodsId");
        Long commentId = info.get("commentId");
        Long type = info.get("type");
        ShopMemberBrowseHistory shopMemberBrowseHistory = new ShopMemberBrowseHistory();
        shopMemberBrowseHistory.setMemberId(userId);
        shopMemberBrowseHistory.setType(Short.parseShort(type.toString()));
        // 如果今天已经访问过了该商品或者店铺或者评论,那么修改该信息的浏览时间即可
        if (storeId != null) {
            // 判断在这次访问之前, 今天是否已经访问了该时间
            ShopMemberBrowseHistory shopMemberBrowseHistory1 = memberBrowseHistoryRepository.findByStoreIdAndMemberIdAndBrowseTimeBetween(storeId, userId, LocalDate.now().atStartOfDay(), LocalDateTime.now());
            if (shopMemberBrowseHistory1 != null) {
                shopMemberBrowseHistory1.setBrowseTime(LocalDateTime.now());
                memberBrowseHistoryRepository.saveAndFlush(shopMemberBrowseHistory1);
                return;
            } else {
                // 没有 -> 则设置地址
                shopMemberBrowseHistory.setStoreId(storeId);
            }
        }
        if (goodsId != null) {
            ShopMemberBrowseHistory shopMemberBrowseHistory1= memberBrowseHistoryRepository.findByGoodsIdAndMemberIdAndBrowseTimeBetween(goodsId, userId, LocalDate.now().atStartOfDay(), LocalDateTime.now());
            if (shopMemberBrowseHistory1 != null) {
                shopMemberBrowseHistory1.setBrowseTime(LocalDateTime.now());
                memberBrowseHistoryRepository.saveAndFlush(shopMemberBrowseHistory1);
                return;
            } else {
                shopMemberBrowseHistory.setGoodsId(goodsId);
            }
        }
        if (commentId != null) {
            ShopMemberBrowseHistory shopMemberBrowseHistory1 = memberBrowseHistoryRepository.findByCommentIdAndMemberIdAndBrowseTimeBetween(commentId, userId, LocalDate.now().atStartOfDay(), LocalDateTime.now());
            if (shopMemberBrowseHistory1 != null) {
                shopMemberBrowseHistory1.setBrowseTime(LocalDateTime.now());
                memberBrowseHistoryRepository.saveAndFlush(shopMemberBrowseHistory1);
            } else {
                shopMemberBrowseHistory.setCommentId(commentId);
            }
        }
        memberBrowseHistoryRepository.save(shopMemberBrowseHistory);
    }

    /**
     * 监听coupon_queue的消息, 对优惠卷进行操作
     *
     * @param info
     */
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
