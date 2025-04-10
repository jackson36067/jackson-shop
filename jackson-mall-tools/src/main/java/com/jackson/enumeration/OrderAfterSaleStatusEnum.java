package com.jackson.enumeration;

import lombok.Getter;

@Getter
public enum OrderAfterSaleStatusEnum {
    MayApply(0), // 用户可申请退款
    HaveApply(1), // 用户已经申请退款
    Approved(2), // 审核通过
    RefundSuccess(3), // 退款成功
    Refuse(4), // 审核拒绝退款
    Cancel(5); // 用户取消退款

    private final Integer type;

    OrderAfterSaleStatusEnum(Integer type) {
        this.type = type;
    }
}
