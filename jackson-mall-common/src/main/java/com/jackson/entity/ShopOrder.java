package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_order")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_sn", nullable = false, length = 63)
    private String orderSn;

    @Column(name = "order_status", nullable = false)
    private Short orderStatus;

    @ColumnDefault("0")
    @Column(name = "aftersale_status")
    private Short afterSaleStatus;

    @Column(name = "consignee", nullable = false, length = 63)
    private String consignee;

    @Column(name = "mobile", nullable = false, length = 63)
    private String mobile;

    @Column(name = "address", nullable = false, length = 127)
    private String address;

    @ColumnDefault("''")
    @Column(name = "message", length = 512)
    private String message;

    @Column(name = "goods_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal goodsPrice;

    @Column(name = "freight_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal freightPrice;

    @Column(name = "coupon_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal couponPrice;

    @Column(name = "order_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal orderPrice;

    @Column(name = "actual_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal actualPrice;

    @Column(name = "pay_id", length = 63)
    private String payId;

    @Column(name = "pay_time")
    private LocalDateTime payTime;

    @Column(name = "pay_type")
    private Short payType;

    @Column(name = "ship_sn", length = 63)
    private String shipSn;

    @Column(name = "ship_channel", length = 63)
    private String shipChannel;

    @Column(name = "ship_time")
    private LocalDateTime shipTime;

    @Column(name = "refund_status")
    private Short refundStatus;

    @ColumnDefault("0.00")
    @Column(name = "refund_amount", precision = 10, scale = 2)
    private BigDecimal refundAmount;

    @Column(name = "refund_type", length = 63)
    private String refundType;

    @Column(name = "refund_content", length = 2000)
    private String refundContent;

    @Column(name = "refund_time")
    private LocalDateTime refundTime;

    @Column(name = "confirm_time")
    private Instant confirmTime;

    @ColumnDefault("0")
    @Column(name = "comments")
    private Short comments;

    @Column(name = "order_end_time")
    private LocalDateTime orderEndTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopOrder() {
    }

    public ShopOrder(Long id, Long userId, String orderSn, Short orderStatus, Short aftersaleStatus, String consignee, String mobile, String address, String message, BigDecimal goodsPrice, BigDecimal freightPrice, BigDecimal couponPrice, BigDecimal orderPrice, BigDecimal actualPrice, String payId, LocalDateTime payTime, Short payType, String shipSn, String shipChannel, LocalDateTime shipTime, Short refundStatus, BigDecimal refundAmount, String refundType, String refundContent, LocalDateTime refundTime, Instant confirmTime, Short comments, LocalDateTime orderEndTime, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
        this.id = id;
        this.userId = userId;
        this.orderSn = orderSn;
        this.orderStatus = orderStatus;
        this.afterSaleStatus = aftersaleStatus;
        this.consignee = consignee;
        this.mobile = mobile;
        this.address = address;
        this.message = message;
        this.goodsPrice = goodsPrice;
        this.freightPrice = freightPrice;
        this.couponPrice = couponPrice;
        this.orderPrice = orderPrice;
        this.actualPrice = actualPrice;
        this.payId = payId;
        this.payTime = payTime;
        this.payType = payType;
        this.shipSn = shipSn;
        this.shipChannel = shipChannel;
        this.shipTime = shipTime;
        this.refundStatus = refundStatus;
        this.refundAmount = refundAmount;
        this.refundType = refundType;
        this.refundContent = refundContent;
        this.refundTime = refundTime;
        this.confirmTime = confirmTime;
        this.comments = comments;
        this.orderEndTime = orderEndTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Short getAfterSaleStatus() {
        return afterSaleStatus;
    }

    public void setAfterSaleStatus(Short aftersaleStatus) {
        this.afterSaleStatus = aftersaleStatus;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public Short getPayType() {
        return payType;
    }

    public void setPayType(Short payType) {
        this.payType = payType;
    }

    public String getShipSn() {
        return shipSn;
    }

    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }

    public String getShipChannel() {
        return shipChannel;
    }

    public void setShipChannel(String shipChannel) {
        this.shipChannel = shipChannel;
    }

    public LocalDateTime getShipTime() {
        return shipTime;
    }

    public void setShipTime(LocalDateTime shipTime) {
        this.shipTime = shipTime;
    }

    public Short getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Short refundStatus) {
        this.refundStatus = refundStatus;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getRefundContent() {
        return refundContent;
    }

    public void setRefundContent(String refundContent) {
        this.refundContent = refundContent;
    }

    public LocalDateTime getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(LocalDateTime refundTime) {
        this.refundTime = refundTime;
    }

    public Instant getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Instant confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Short getComments() {
        return comments;
    }

    public void setComments(Short comments) {
        this.comments = comments;
    }

    public LocalDateTime getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(LocalDateTime orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrder shopOrder = (ShopOrder) o;
        return Objects.equals(id, shopOrder.id) && Objects.equals(userId, shopOrder.userId) && Objects.equals(orderSn, shopOrder.orderSn) && Objects.equals(orderStatus, shopOrder.orderStatus) && Objects.equals(afterSaleStatus, shopOrder.afterSaleStatus) && Objects.equals(consignee, shopOrder.consignee) && Objects.equals(mobile, shopOrder.mobile) && Objects.equals(address, shopOrder.address) && Objects.equals(message, shopOrder.message) && Objects.equals(goodsPrice, shopOrder.goodsPrice) && Objects.equals(freightPrice, shopOrder.freightPrice) && Objects.equals(couponPrice, shopOrder.couponPrice) && Objects.equals(orderPrice, shopOrder.orderPrice) && Objects.equals(actualPrice, shopOrder.actualPrice) && Objects.equals(payId, shopOrder.payId) && Objects.equals(payTime, shopOrder.payTime) && Objects.equals(payType, shopOrder.payType) && Objects.equals(shipSn, shopOrder.shipSn) && Objects.equals(shipChannel, shopOrder.shipChannel) && Objects.equals(shipTime, shopOrder.shipTime) && Objects.equals(refundStatus, shopOrder.refundStatus) && Objects.equals(refundAmount, shopOrder.refundAmount) && Objects.equals(refundType, shopOrder.refundType) && Objects.equals(refundContent, shopOrder.refundContent) && Objects.equals(refundTime, shopOrder.refundTime) && Objects.equals(confirmTime, shopOrder.confirmTime) && Objects.equals(comments, shopOrder.comments) && Objects.equals(orderEndTime, shopOrder.orderEndTime) && Objects.equals(createTime, shopOrder.createTime) && Objects.equals(updateTime, shopOrder.updateTime) && Objects.equals(delFlag, shopOrder.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderSn, orderStatus, afterSaleStatus, consignee, mobile, address, message, goodsPrice, freightPrice, couponPrice, orderPrice, actualPrice, payId, payTime, payType, shipSn, shipChannel, shipTime, refundStatus, refundAmount, refundType, refundContent, refundTime, confirmTime, comments, orderEndTime, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderSn='" + orderSn + '\'' +
                ", orderStatus=" + orderStatus +
                ", aftersaleStatus=" + afterSaleStatus +
                ", consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", message='" + message + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", freightPrice=" + freightPrice +
                ", couponPrice=" + couponPrice +
                ", orderPrice=" + orderPrice +
                ", actualPrice=" + actualPrice +
                ", payId='" + payId + '\'' +
                ", payTime=" + payTime +
                ", payType=" + payType +
                ", shipSn='" + shipSn + '\'' +
                ", shipChannel='" + shipChannel + '\'' +
                ", shipTime=" + shipTime +
                ", refundStatus=" + refundStatus +
                ", refundAmount=" + refundAmount +
                ", refundType='" + refundType + '\'' +
                ", refundContent='" + refundContent + '\'' +
                ", refundTime=" + refundTime +
                ", confirmTime=" + confirmTime +
                ", comments=" + comments +
                ", orderEndTime=" + orderEndTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}