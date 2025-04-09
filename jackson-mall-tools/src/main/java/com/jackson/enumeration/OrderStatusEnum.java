package com.jackson.enumeration;

import lombok.Getter;

/**
 * 订单状态枚举类
 */
@Getter
public enum OrderStatusEnum {
    Unpaid(101), // 订单生成,但用户还没支付
    Cancel(102), // 下单未支付用户取消订单
    Expire(103), // 下单未支付,订单超时
    Unshipped(201), // 支付完成, 商家未发货
    Refund(202), // 已支付,未发货,用户申请退款
    RefundSuccess(203), // 管理员执行退款操作, 确认退款成功
    shipped(301), // 商家发货
    Receipt(401), // 用户确认收货,订单结束
    AutoReceipt(402); // 用户没有确认收货, 快递反馈已收货后,超过一段时间, 自动确认收货



    private final Integer type;

    OrderStatusEnum(Integer type) {
        this.type = type;
    }
}
