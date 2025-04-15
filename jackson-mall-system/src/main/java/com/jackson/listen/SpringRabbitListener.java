package com.jackson.listen;

import com.jackson.constant.RabbitMQConstant;
import com.jackson.entity.*;
import com.jackson.exception.InventoryNotSufficientException;
import com.jackson.repository.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
    private MemberCouponRepository memberCouponRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private GoodsProductRepository goodsProductRepository;
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Resource
    private CartRepository cartRepository;

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
            ShopMemberBrowseHistory shopMemberBrowseHistory1 = memberBrowseHistoryRepository.findByGoodsIdAndMemberIdAndBrowseTimeBetween(goodsId, userId, LocalDate.now().atStartOfDay(), LocalDateTime.now());
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
        memberCouponRepository.save(shopMemberCoupon);
    }

    /**
     * 监听用户购物时使用的优惠卷
     *
     * @param memberCouponIdList 用户使用的优惠卷id集合
     */
    @RabbitListener(queues = RabbitMQConstant.ORDER_COUPON_QUEUE)
    public void listenOrderUseCoupon(List<Long> memberCouponIdList) {
        // 将所有使用的优惠卷改为使用状态
        memberCouponRepository.updateAllUseStatus(memberCouponIdList, (short) 1);
        log.info("监听到订单中使用了优惠卷");
    }

    /**
     * 监听购物时购买商品规格的数量,对数量进行删减
     *
     * @param specQuantities 订单中商品规格以及数量数据
     */
    @RabbitListener(queues = RabbitMQConstant.ORDER_PRODUCT_QUEUE, concurrency = "5-10")
    public void listenOrderPurchaseProductNumber(Map<Long, Integer> specQuantities) {
        specQuantities.forEach((k, v) -> {
            ShopGoodsProduct shopGoodsProduct = goodsProductRepository.findByIdAndNumberAfter(k, v);
            if (shopGoodsProduct == null) {
                throw new InventoryNotSufficientException("库存不足");
            }
        });
        // 批量修改商品规格剩余库存
        // 动态生成 CASE WHEN 语句
        StringBuilder queryBuilder = new StringBuilder("UPDATE shop_goods_product ps SET ps.number = ps.number - CASE ");

        // 存储查询参数
        Map<String, Object> updateParams = new HashMap<>();

        // 遍历 map，构建动态查询
        for (Map.Entry<Long, Integer> entry : specQuantities.entrySet()) {
            Long specId = entry.getKey();
            Integer quantity = entry.getValue();
            queryBuilder.append("WHEN ps.id = :id" + specId + " THEN :number" + specId + " ");

            // 将 specId 和 quantity 添加到参数中
            updateParams.put("id" + specId, specId);
            updateParams.put("number" + specId, quantity);
        }

        // 完成 CASE WHEN 语句
        queryBuilder.append("ELSE 0 END WHERE ps.id IN (:idList)");

        // 将所有的 specId 提取出来，作为查询参数传入
        updateParams.put("idList", specQuantities.keySet());

        // 执行更新操作
        String sql = queryBuilder.toString();
        namedParameterJdbcTemplate.update(sql, updateParams);
        log.info("监听购物减少商品库存");
    }

    /**
     * 监听购物时,增加商品的实际售卖量
     * @param orderGoodsInfo 商品id以及售卖数量map
     */
    @RabbitListener(queues = RabbitMQConstant.ORDER_GOODS_QUEUE, concurrency = "5-10")
    public void listenOrderGoods(Map<Long, Integer> orderGoodsInfo) {
        // 修改商品实际售卖数量
        // 动态生成 CASE WHEN 语句
        StringBuilder queryBuilder = new StringBuilder("UPDATE shop_goods sg SET sg.actual_sales = sg.actual_sales + CASE ");

        // 存储查询参数
        Map<String, Object> updateParams = new HashMap<>();

        // 遍历 map，构建动态查询
        for (Map.Entry<Long, Integer> entry : orderGoodsInfo.entrySet()) {
            Long goodsId = entry.getKey();
            Integer salesCount = entry.getValue();
            queryBuilder.append("WHEN sg.id = :id" + goodsId + " THEN :actual_sales" + goodsId + " ");
            // 将商品id以及售卖数量添加到更新参数中
            updateParams.put("id" + goodsId, goodsId);
            updateParams.put("actual_sales" + goodsId, salesCount);
        }

        // 完成 CASE WHEN 语句
        queryBuilder.append("ELSE 0 END WHERE sg.id IN (:idList)");

        // 将所有的 specId 提取出来，作为查询参数传入
        updateParams.put("idList", orderGoodsInfo.keySet());

        // 执行更新操作
        String sql = queryBuilder.toString();
        namedParameterJdbcTemplate.update(sql, updateParams);
        log.info("监听购物增加商品实际售卖量");
    }

    /**
     * 监听从购物车中购物,删除购物车中相关商品
     * @param cartGoodsIdList 购物车商品id
     */
    @RabbitListener(queues = RabbitMQConstant.ORDER_CART_QUEUE)
    public void listenOrderCartGoodsList(List<Long> cartGoodsIdList) {
        cartRepository.deleteAllByIdInBatch(cartGoodsIdList);
        log.info("监听购买购物车商品");
    }
}
