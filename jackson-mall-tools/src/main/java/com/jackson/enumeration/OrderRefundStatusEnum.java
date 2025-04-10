package com.jackson.enumeration;

import lombok.Getter;

@Getter
public enum OrderRefundStatusEnum {
    NotRefund(0), // 为退款
    ApplyRefund(1), // 申请退款
    RefundSuccess(2), // 退款成功
    RefundFail(3); // 退款失败

    private final Integer type;

    OrderRefundStatusEnum(Integer type) {
        this.type = type;
    }
}
