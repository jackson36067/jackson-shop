package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.OrderDTO;
import com.jackson.entity.ShopOrder;
import com.jackson.entity.ShopOrderGoods;
import com.jackson.enumeration.OrderAfterSaleStatusEnum;
import com.jackson.enumeration.OrderStatusEnum;
import com.jackson.repository.GoodsProductRepository;
import com.jackson.repository.OrderRepository;
import com.jackson.result.Result;
import com.jackson.service.OrderService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private GoodsProductRepository goodsProductRepository;

    /**
     * 生成订单
     *
     * @param orderDTO 订单相关信息
     * @return
     */
    @Transactional
    public Result<String> generateOrder(OrderDTO orderDTO) {
        ShopOrder shopOrder = BeanUtil.copyProperties(orderDTO, ShopOrder.class);
        shopOrder.setUserId(BaseContext.getCurrentId());
        String orderSn = UUID.randomUUID().toString();
        shopOrder.setOrderSn(orderSn);
        shopOrder.setOrderStatus(orderDTO.getPayStatus() ? OrderStatusEnum.Unshipped.getType() : OrderStatusEnum.Unpaid.getType());
        shopOrder.setAfterSaleStatus(OrderAfterSaleStatusEnum.MayApply.getType());
        shopOrder.setActualPrice(orderDTO.getOrderPrice());
        if (orderDTO.getPayStatus()) {
            shopOrder.setPayTime(LocalDateTime.now());
        } else {
            // 没有付款 -> 设置订单结束时间
            shopOrder.setOrderEndTime(LocalDateTime.now().plusMinutes(30));
        }
        shopOrder.setPayType(1);
        List<ShopOrderGoods> ShopOrderGoodsList = orderDTO.getOrderGoodsList()
                .stream()
                .map(orderGoodsDTO -> {
                    ShopOrderGoods shopOrderGoods = BeanUtil.copyProperties(orderGoodsDTO, ShopOrderGoods.class);
                    // 设置所选商品的规格
                    shopOrderGoods.setSpecifications(JSONUtil.toJsonStr(orderGoodsDTO.getSpecification()));
                    // 设置商品可评价
                    shopOrderGoods.setComment(0);
                    // 设置订单id
                    shopOrderGoods.setShopOrder(shopOrder);
                    return shopOrderGoods;
                })
                .toList();
        // 设置订单的商品信息
        shopOrder.setShopOrderGoodsList(ShopOrderGoodsList);
        orderRepository.save(shopOrder);
        // 异步更改商品库存信息
        Map<Long, Integer> info = new HashMap<>();
        orderDTO.getOrderGoodsList().forEach(orderGoods -> {
            info.put(orderGoods.getProductId(), orderGoods.getNumber());
        });
        rabbitTemplate.convertAndSend(RabbitMQConstant.ORDER_PRODUCT_QUEUE, info);
        // 异步处理这次购买商品使用的优惠卷
        if (orderDTO.getUseCouponIdList() != null && !orderDTO.getUseCouponIdList().isEmpty()) {
            rabbitTemplate.convertAndSend(RabbitMQConstant.ORDER_COUPON_QUEUE, orderDTO.getUseCouponIdList());
        }
        return Result.success(orderSn);
    }
}