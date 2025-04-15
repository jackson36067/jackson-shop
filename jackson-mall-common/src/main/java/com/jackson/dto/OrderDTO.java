package com.jackson.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class OrderDTO implements Serializable {
    private String consignee; // 收货人名称
    private String mobile; // 收货人手机号
    private String address; // 收货人地址
    private String message; // 订单留言
    private BigDecimal goodsPrice; // 商品总价格
    private BigDecimal freightPrice; // 运费
    private BigDecimal couponPrice; // 优惠卷价格
    private BigDecimal orderPrice; // 用户最终付费 goodsPrice + freightPrice - couponPrice
    private Boolean payStatus; // 是否付款
    private List<OrderGoodsDTO> orderGoodsList; // 订单商品列表
    private List<Long> useCouponIdList; // 订单使用优惠卷列表
    private List<Long> cartIdList;

    public OrderDTO() {
    }

    public OrderDTO(String consignee, String mobile, String address, String message, BigDecimal goodsPrice, BigDecimal freightPrice, BigDecimal couponPrice, BigDecimal orderPrice, Boolean payStatus, List<OrderGoodsDTO> orderGoodsList, List<Long> useCouponIdList, List<Long> cartIdList) {
        this.consignee = consignee;
        this.mobile = mobile;
        this.address = address;
        this.message = message;
        this.goodsPrice = goodsPrice;
        this.freightPrice = freightPrice;
        this.couponPrice = couponPrice;
        this.orderPrice = orderPrice;
        this.payStatus = payStatus;
        this.orderGoodsList = orderGoodsList;
        this.useCouponIdList = useCouponIdList;
        this.cartIdList = cartIdList;
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

    public Boolean getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Boolean payStatus) {
        this.payStatus = payStatus;
    }

    public List<OrderGoodsDTO> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoodsDTO> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public List<Long> getUseCouponIdList() {
        return useCouponIdList;
    }

    public void setUseCouponIdList(List<Long> useCouponIdList) {
        this.useCouponIdList = useCouponIdList;
    }

    public List<Long> getCartIdList() {
        return cartIdList;
    }

    public void setCartIdList(List<Long> cartIdList) {
        this.cartIdList = cartIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(consignee, orderDTO.consignee) && Objects.equals(mobile, orderDTO.mobile) && Objects.equals(address, orderDTO.address) && Objects.equals(message, orderDTO.message) && Objects.equals(goodsPrice, orderDTO.goodsPrice) && Objects.equals(freightPrice, orderDTO.freightPrice) && Objects.equals(couponPrice, orderDTO.couponPrice) && Objects.equals(orderPrice, orderDTO.orderPrice) && Objects.equals(payStatus, orderDTO.payStatus) && Objects.equals(orderGoodsList, orderDTO.orderGoodsList) && Objects.equals(useCouponIdList, orderDTO.useCouponIdList) && Objects.equals(cartIdList, orderDTO.cartIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consignee, mobile, address, message, goodsPrice, freightPrice, couponPrice, orderPrice, payStatus, orderGoodsList, useCouponIdList, cartIdList);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", message='" + message + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", freightPrice=" + freightPrice +
                ", couponPrice=" + couponPrice +
                ", orderPrice=" + orderPrice +
                ", payStatus=" + payStatus +
                ", orderGoodsList=" + orderGoodsList +
                ", useCouponIdList=" + useCouponIdList +
                ", cartIdList=" + cartIdList +
                '}';
    }
}