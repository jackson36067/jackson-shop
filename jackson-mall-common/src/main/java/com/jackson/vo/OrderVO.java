package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class OrderVO implements Serializable {
    private Long id; // 订单信息
    private String orderSn; // 订单编号
    private Integer orderStatus;
    private List<OrderGoodsVO> orderGoodsList;
    private BigDecimal orderPrice;

    public OrderVO() {
    }

    public OrderVO(Long id, String orderSn, Integer orderStatus, List<OrderGoodsVO> orderGoodList, BigDecimal orderPrice) {
        this.id = id;
        this.orderSn = orderSn;
        this.orderStatus = orderStatus;
        this.orderGoodsList = orderGoodList;
        this.orderPrice = orderPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderGoodsVO> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoodsVO> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderVO orderVO = (OrderVO) o;
        return Objects.equals(id, orderVO.id) && Objects.equals(orderSn, orderVO.orderSn) && Objects.equals(orderStatus, orderVO.orderStatus) && Objects.equals(orderGoodsList, orderVO.orderGoodsList) && Objects.equals(orderPrice, orderVO.orderPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderSn, orderStatus, orderGoodsList, orderPrice);
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "id=" + id +
                ", orderSn='" + orderSn + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderGoodList=" + orderGoodsList +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
