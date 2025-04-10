package com.jackson.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class OrderGoodsDTO {
    private Long goodsId;
    private String goodsName;
    private String goodsSn;
    private Long productId;
    private Integer number;
    private BigDecimal price;
    private Map<String, String> specification;
    private String picUrl;

    public OrderGoodsDTO() {
    }

    public OrderGoodsDTO(Long goodsId, String goodsName, String goodsSn, Long productId, Integer number, BigDecimal price, Map<String, String> specification, String picUrl) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsSn = goodsSn;
        this.productId = productId;
        this.number = number;
        this.price = price;
        this.specification = specification;
        this.picUrl = picUrl;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<String, String> getSpecification() {
        return specification;
    }

    public void setSpecification(Map<String, String> specification) {
        this.specification = specification;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderGoodsDTO that = (OrderGoodsDTO) o;
        return Objects.equals(goodsId, that.goodsId) && Objects.equals(goodsName, that.goodsName) && Objects.equals(goodsSn, that.goodsSn) && Objects.equals(productId, that.productId) && Objects.equals(number, that.number) && Objects.equals(price, that.price) && Objects.equals(specification, that.specification) && Objects.equals(picUrl, that.picUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, goodsSn, productId, number, price, specification, picUrl);
    }

    @Override
    public String toString() {
        return "OrderGoodsDTO{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSn='" + goodsSn + '\'' +
                ", productId=" + productId +
                ", number=" + number +
                ", price=" + price +
                ", specification='" + specification + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
