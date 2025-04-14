package com.jackson.vo;

import java.util.Objects;

public class OrderDataVO {
    private Integer unPaymentOrderNumber;
    private Integer unShippedOrderNumber;
    private Integer unReceiptOrderNumber;
    private Integer completedOrderNumber;
    private Integer refundOrderNumber;

    public OrderDataVO() {
    }

    public OrderDataVO(Integer unPaymentOrderNumber, Integer unShippedOrderNumber, Integer unReceiptOrderNumber, Integer completedOrderNumber, Integer refundOrderNumber) {
        this.unPaymentOrderNumber = unPaymentOrderNumber;
        this.unShippedOrderNumber = unShippedOrderNumber;
        this.unReceiptOrderNumber = unReceiptOrderNumber;
        this.completedOrderNumber = completedOrderNumber;
        this.refundOrderNumber = refundOrderNumber;
    }

    public Integer getUnPaymentOrderNumber() {
        return unPaymentOrderNumber;
    }

    public void setUnPaymentOrderNumber(Integer unPaymentOrderNumber) {
        this.unPaymentOrderNumber = unPaymentOrderNumber;
    }

    public Integer getUnShippedOrderNumber() {
        return unShippedOrderNumber;
    }

    public void setUnShippedOrderNumber(Integer unShippedOrderNumber) {
        this.unShippedOrderNumber = unShippedOrderNumber;
    }

    public Integer getUnReceiptOrderNumber() {
        return unReceiptOrderNumber;
    }

    public void setUnReceiptOrderNumber(Integer unReceiptOrderNumber) {
        this.unReceiptOrderNumber = unReceiptOrderNumber;
    }

    public Integer getCompletedOrderNumber() {
        return completedOrderNumber;
    }

    public void setCompletedOrderNumber(Integer completedOrderNumber) {
        this.completedOrderNumber = completedOrderNumber;
    }

    public Integer getRefundOrderNumber() {
        return refundOrderNumber;
    }

    public void setRefundOrderNumber(Integer refundOrderNumber) {
        this.refundOrderNumber = refundOrderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDataVO that = (OrderDataVO) o;
        return Objects.equals(unPaymentOrderNumber, that.unPaymentOrderNumber) && Objects.equals(unShippedOrderNumber, that.unShippedOrderNumber) && Objects.equals(unReceiptOrderNumber, that.unReceiptOrderNumber) && Objects.equals(completedOrderNumber, that.completedOrderNumber) && Objects.equals(refundOrderNumber, that.refundOrderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unPaymentOrderNumber, unShippedOrderNumber, unReceiptOrderNumber, completedOrderNumber, refundOrderNumber);
    }

    @Override
    public String toString() {
        return "OrderDataVO{" +
                "unPaymentOrderNumber=" + unPaymentOrderNumber +
                ", unShippedOrderNumber=" + unShippedOrderNumber +
                ", unReceiptOrderNumber=" + unReceiptOrderNumber +
                ", completedOrderNumber=" + completedOrderNumber +
                ", refundOrderNumber=" + refundOrderNumber +
                '}';
    }
}