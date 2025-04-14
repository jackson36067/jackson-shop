package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.GoodsConstant;
import com.jackson.constant.OrderConstant;
import com.jackson.constant.RabbitMQConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.OrderDTO;
import com.jackson.entity.ShopOrder;
import com.jackson.entity.ShopOrderGoods;
import com.jackson.entity.ShopStore;
import com.jackson.enumeration.OrderAfterSaleStatusEnum;
import com.jackson.enumeration.OrderStatusEnum;
import com.jackson.enumeration.OrderTypeEnum;
import com.jackson.repository.GoodsRepository;
import com.jackson.repository.OrderRepository;
import com.jackson.result.Result;
import com.jackson.service.OrderService;
import com.jackson.vo.OrderDataVO;
import com.jackson.vo.OrderGoodsVO;
import com.jackson.vo.OrderVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private GoodsRepository goodsRepository;

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
        long currentTimeMills = System.currentTimeMillis();
        // 使用时间戳以及随机的三位数字作为订单编号
        String str = RandomUtil.randomNumbers(3);
        String orderSn = currentTimeMills + str;
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
        List<ShopOrderGoods> ShopOrderGoodsList = orderDTO.getOrderGoodsList().stream().map(orderGoodsDTO -> {
            ShopOrderGoods shopOrderGoods = BeanUtil.copyProperties(orderGoodsDTO, ShopOrderGoods.class);
            // 设置所选商品的规格
            shopOrderGoods.setSpecifications(JSONUtil.toJsonStr(orderGoodsDTO.getSpecification()));
            // 设置商品可评价
            shopOrderGoods.setComment(0);
            // 设置订单id
            shopOrderGoods.setShopOrder(shopOrder);
            return shopOrderGoods;
        }).toList();
        // 设置订单的商品信息
        shopOrder.setShopOrderGoodsList(ShopOrderGoodsList);
        orderRepository.save(shopOrder);
        // 异步更改商品库存信息
        Map<Long, Integer> orderProductInfo = new HashMap<>();
        orderDTO.getOrderGoodsList().forEach(orderGoods -> {
            orderProductInfo.put(orderGoods.getProductId(), orderGoods.getNumber());
        });
        rabbitTemplate.convertAndSend(RabbitMQConstant.ORDER_PRODUCT_QUEUE, orderProductInfo);
        // 异步处理这次购买商品使用的优惠卷
        if (orderDTO.getUseCouponIdList() != null && !orderDTO.getUseCouponIdList().isEmpty()) {
            rabbitTemplate.convertAndSend(RabbitMQConstant.ORDER_COUPON_QUEUE, orderDTO.getUseCouponIdList());
        }
        // 异步更改商品被购买数量
        Map<Long, Integer> orderGoodsInfo = new HashMap<>();
        orderDTO.getOrderGoodsList().forEach(orderGoods -> {
            // 判断是否已经存在key,存在对数据类型累加
            if (orderGoodsInfo.containsKey(orderGoods.getGoodsId())) {
                orderGoodsInfo.compute(orderGoods.getGoodsId(), (k, purchaseCount) -> purchaseCount + orderGoods.getNumber());
            } else {
                orderGoodsInfo.put(orderGoods.getGoodsId(), orderGoods.getNumber());
            }
        });
        rabbitTemplate.convertAndSend(RabbitMQConstant.ORDER_GOODS_QUEUE, orderGoodsInfo);
        return Result.success(orderSn);
    }

    /**
     * @param type                    获取订单类型 0.全部  1.待支付 2.代发货 3.待收货 4.已完成
     * @param goodsNameOrOrderSnParam 订单编号或者订单中商品名称
     * @param placeOrderTimeParam     下单范围-开始时间
     * @param placeOrderEndTimeParam  下单范围-结束时间
     * @return 订单数据列表
     */
    public Result<List<OrderVO>> getOrderList(Integer type, String goodsNameOrOrderSnParam, LocalDateTime placeOrderTimeParam, LocalDateTime placeOrderEndTimeParam) {
        Specification<ShopOrder> shopOrderSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicateArrayList = new ArrayList<>();
            OrderTypeEnum orderStatusEnum = Arrays.stream(OrderTypeEnum.values()).filter(status -> status.getOrderType().equals(type))  // 根据 type 值匹配
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid order status"));
            switch (orderStatusEnum) {
                // 获取所有订单
                case ALL:
                    break;
                // 获取待支付订单
                case PendingPayment:
                    Predicate pendingPaymentCondition = cb.equal(root.get(OrderConstant.ORDER_STATUS), OrderStatusEnum.Unpaid.getType());
                    predicateArrayList.add(pendingPaymentCondition);
                    break;
                // 获取待发货订单
                case PendingShipment:
                    Predicate pendingShipmentCondition = cb.equal(root.get(OrderConstant.ORDER_STATUS), OrderStatusEnum.Unshipped.getType());
                    predicateArrayList.add(pendingShipmentCondition);
                    break;
                // 获取待收货订单
                case PendingReceipt:
                    Predicate pendingReceiptCondition = cb.equal(root.get(OrderConstant.ORDER_STATUS), OrderStatusEnum.shipped.getType());
                    predicateArrayList.add(pendingReceiptCondition);
                    break;
                // 获取已完成订单(包括用户确认以及系统自动确认)
                case Complete:
                    Predicate completeCondition = cb.or(cb.equal(root.get(OrderConstant.ORDER_STATUS), OrderStatusEnum.Receipt.getType()), cb.equal(root.get(OrderConstant.ORDER_STATUS), OrderStatusEnum.AutoReceipt.getType()));
                    predicateArrayList.add(completeCondition);
                    break;
                // 获取退款订单
                case Refund:
                    Predicate refundCondition = cb.or(cb.equal(root.get(OrderConstant.ORDER_STATUS), OrderStatusEnum.Refund.getType()), cb.equal(root.get(OrderConstant.ORDER_STATUS), OrderStatusEnum.RefundSuccess.getType()));
                    predicateArrayList.add(refundCondition);
                    break;
            }
            if (goodsNameOrOrderSnParam != null) {
                // 在 Order 实体和 OrderGoods 实体之间建立关联
                Join<ShopOrder, ShopOrderGoods> join = root.join(OrderConstant.ORDER_GOODS, JoinType.INNER);
                // 封装条件, 可能为订单中商品的名称或者订单编号
                Predicate goodsNameOrOrderSnCondition = cb.or(cb.like(join.get(GoodsConstant.GOODS_NAME), "%" + goodsNameOrOrderSnParam + "%"), cb.like(root.get(OrderConstant.ORDER_SN), "%" + goodsNameOrOrderSnParam + "%"));
                predicateArrayList.add(goodsNameOrOrderSnCondition);
            }
            if (placeOrderTimeParam != null && placeOrderEndTimeParam != null) {
                Predicate placeOrderTimeCondition = cb.between(root.get(OrderConstant.CREATE_TIME), placeOrderTimeParam, placeOrderEndTimeParam);
                predicateArrayList.add(placeOrderTimeCondition);
            }
            return cb.and(predicateArrayList.toArray(new Predicate[0]));
        };
        List<ShopOrder> shopOrderList = orderRepository.findAll(shopOrderSpecification, Sort.by(Sort.Direction.DESC, OrderConstant.CREATE_TIME));
        List<OrderVO> orderVoList = shopOrderList.stream().map(shopOrder -> {
            OrderVO orderVO = BeanUtil.copyProperties(shopOrder, OrderVO.class);
            // 封装订单状态
            OrderStatusEnum orderStatusEnum = Arrays.stream(OrderStatusEnum.values()).filter(status -> status.getType().equals(shopOrder.getOrderStatus()))  // 根据 type 值匹配
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid order status"));
            switch (orderStatusEnum) {
                case Unpaid:
                    orderVO.setOrderStatus(1);
                    break;
                case Unshipped:
                    orderVO.setOrderStatus(2);
                    break;
                case shipped:
                    orderVO.setOrderStatus(3);
                    break;
                case Receipt, AutoReceipt:
                    orderVO.setOrderStatus(4);
                    break;
                // 申请退款返回类型为5
                case Refund:
                    orderVO.setOrderStatus(5);
                    break;
                // 退款成功返回类型为6
                case RefundSuccess:
                    orderVO.setOrderStatus(6);
                    break;
                // 订单取消或者超时自动取消返回类行为0
                case Cancel, Expire:
                    orderVO.setOrderStatus(0);
                    break;
            }
            List<OrderGoodsVO> orderGoodsVOList = shopOrder.getShopOrderGoodsList().stream().map(orderGoods -> {
                OrderGoodsVO orderGoodsVO = BeanUtil.copyProperties(orderGoods, OrderGoodsVO.class);
                // 疯涨商品id
                orderGoodsVO.setGoodsId(orderGoods.getGoodsId());
                List<String> specification = new ArrayList<>();
                // 封装商品规格
                Map<String, String> specificationMap = JSONUtil.toBean(orderGoods.getSpecifications(), Map.class);
                specificationMap.forEach((key, value) -> specification.add(value));
                orderGoodsVO.setSpecifications(specification);
                // 封装商品店铺名称以及id
                ShopStore shopStore = goodsRepository.findById(orderGoods.getGoodsId()).get().getShopStore();
                orderGoodsVO.setStoreId(shopStore.getId());
                orderGoodsVO.setStoreName(shopStore.getName());
                return orderGoodsVO;
            }).toList();
            orderVO.setOrderGoodsList(orderGoodsVOList);
            return orderVO;
        }).toList();
        return Result.success(orderVoList);
    }

    /**
     * 获取订单各类型数量数据
     *
     * @return 订单各个类型数量数据
     */
    public Result<OrderDataVO> getOrderTypeNumberData() {
        OrderDataVO orderDataVO = new OrderDataVO();
        // 用户没用登录, 返回空数据
        orderDataVO.setUnPaymentOrderNumber(0);
        orderDataVO.setUnShippedOrderNumber(0);
        orderDataVO.setUnReceiptOrderNumber(0);
        orderDataVO.setCompletedOrderNumber(0);
        orderDataVO.setRefundOrderNumber(0);
        if (BaseContext.getCurrentId() == null) {
            return Result.success(orderDataVO);
        }
        // 获取用户所有订单状态以及订单数量 , object数组索引0为订单状态 1为订单数量
        List<Object[]> orderStatusCountList = orderRepository.findOrderCountByUserIdGroupByStatus(BaseContext.getCurrentId());
        // 遍历分类结果
        orderStatusCountList.forEach(data -> {
            Integer orderStatus = (Integer) data[0];
            Long count = (Long) data[1];
            OrderStatusEnum orderStatusEnum = Arrays.stream(OrderStatusEnum.values()).filter(status -> status.getType().equals(orderStatus))  // 根据 type 值匹配
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid order status"));
            switch (orderStatusEnum) {
                case Unpaid:
                    orderDataVO.setUnPaymentOrderNumber(count.intValue());
                    break;
                case Unshipped:
                    orderDataVO.setUnShippedOrderNumber(count.intValue());
                    break;
                case shipped:
                    orderDataVO.setUnReceiptOrderNumber(count.intValue());
                    break;
                case Receipt, AutoReceipt:
                    orderDataVO.setCompletedOrderNumber(count.intValue());
                    break;
                // 申请退款返回类型为5
                case Refund, RefundSuccess:
                    orderDataVO.setRefundOrderNumber(count.intValue());
                    break;
            }
        });
        return Result.success(orderDataVO);
    }
}