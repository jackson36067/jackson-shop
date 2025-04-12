package com.jackson.enumeration;

import lombok.Getter;

/**
 * 订单类型枚举类
 */
@Getter
public enum OrderTypeEnum {
    ALL(0), // 全部订单
    PendingPayment(1), // 待支付订单
    PendingShipment(2), // 代发货
    PendingReceipt(3), // 待收货
    Complete(4); // 已完成

    private final Integer orderType;

    OrderTypeEnum(Integer orderType) {
        this.orderType = orderType;
    }
}
