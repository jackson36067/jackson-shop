package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class OrderDetailVO implements Serializable {
   private Long id;
   private Long orderId;
   private String orderSn;
   private String address;
   private String mobile;
   private String consignee;
   private Integer orderStatus;
   private BigDecimal goodsPrice;
   private BigDecimal orderPrice;
   private BigDecimal freightPrice;
   private BigDecimal couponPrice;
   private List<OrderGoodsVO> orderGoodsList;

   public OrderDetailVO() {
   }

   public OrderDetailVO(Long id, Long orderId, String orderSn, String address, String mobile, String consignee, Integer orderStatus, BigDecimal goodsPrice, BigDecimal orderPrice, BigDecimal freightPrice, BigDecimal couponPrice, List<OrderGoodsVO> orderGoodsList) {
      this.id = id;
      this.orderId = orderId;
      this.orderSn = orderSn;
      this.address = address;
      this.mobile = mobile;
      this.consignee = consignee;
      this.orderStatus = orderStatus;
      this.goodsPrice = goodsPrice;
      this.orderPrice = orderPrice;
      this.freightPrice = freightPrice;
      this.couponPrice = couponPrice;
      this.orderGoodsList = orderGoodsList;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getOrderId() {
      return orderId;
   }

   public void setOrderId(Long orderId) {
      this.orderId = orderId;
   }

   public String getOrderSn() {
      return orderSn;
   }

   public void setOrderSn(String orderSn) {
      this.orderSn = orderSn;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getConsignee() {
      return consignee;
   }

   public void setConsignee(String consignee) {
      this.consignee = consignee;
   }

   public Integer getOrderStatus() {
      return orderStatus;
   }

   public void setOrderStatus(Integer orderStatus) {
      this.orderStatus = orderStatus;
   }

   public BigDecimal getGoodsPrice() {
      return goodsPrice;
   }

   public void setGoodsPrice(BigDecimal goodsPrice) {
      this.goodsPrice = goodsPrice;
   }

   public BigDecimal getOrderPrice() {
      return orderPrice;
   }

   public void setOrderPrice(BigDecimal orderPrice) {
      this.orderPrice = orderPrice;
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

   public List<OrderGoodsVO> getOrderGoodsList() {
      return orderGoodsList;
   }

   public void setOrderGoodsList(List<OrderGoodsVO> orderGoodsList) {
      this.orderGoodsList = orderGoodsList;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      OrderDetailVO that = (OrderDetailVO) o;
      return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(orderSn, that.orderSn) && Objects.equals(address, that.address) && Objects.equals(mobile, that.mobile) && Objects.equals(consignee, that.consignee) && Objects.equals(orderStatus, that.orderStatus) && Objects.equals(goodsPrice, that.goodsPrice) && Objects.equals(orderPrice, that.orderPrice) && Objects.equals(freightPrice, that.freightPrice) && Objects.equals(couponPrice, that.couponPrice) && Objects.equals(orderGoodsList, that.orderGoodsList);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, orderId, orderSn, address, mobile, consignee, orderStatus, goodsPrice, orderPrice, freightPrice, couponPrice, orderGoodsList);
   }

   @Override
   public String toString() {
      return "OrderDetailVO{" +
              "id=" + id +
              ", orderId=" + orderId +
              ", orderSn='" + orderSn + '\'' +
              ", address='" + address + '\'' +
              ", mobile='" + mobile + '\'' +
              ", consignee='" + consignee + '\'' +
              ", orderStatus=" + orderStatus +
              ", goodsPrice=" + goodsPrice +
              ", orderPrice=" + orderPrice +
              ", freightPrice=" + freightPrice +
              ", couponPrice=" + couponPrice +
              ", orderGoodsList=" + orderGoodsList +
              '}';
   }
}