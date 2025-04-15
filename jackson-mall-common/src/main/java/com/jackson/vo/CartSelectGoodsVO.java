package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class CartSelectGoodsVO implements Serializable {
    private Long id;
    private Long goodsId;
    private Long productId;
    private String goodsName;
    private String goodsSn;
    private Map<String, String> specifications;
    private Short number;
    private BigDecimal price;
    private String picUrl;

    public CartSelectGoodsVO() {
    }

    public CartSelectGoodsVO(Long id, Long goodsId, Long produceId, String goodsName, String goodsSn, Map<String, String> specifications, Short number, BigDecimal price, String picUrl) {
        this.id = id;
        this.goodsId = goodsId;
        this.productId = produceId;
        this.goodsName = goodsName;
        this.goodsSn = goodsSn;
        this.specifications = specifications;
        this.number = number;
        this.price = price;
        this.picUrl = picUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Map<String, String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Map<String, String> specifications) {
        this.specifications = specifications;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        CartSelectGoodsVO that = (CartSelectGoodsVO) o;
        return Objects.equals(id, that.id) && Objects.equals(goodsId, that.goodsId) && Objects.equals(productId, that.productId) && Objects.equals(goodsName, that.goodsName) && Objects.equals(goodsSn, that.goodsSn) && Objects.equals(specifications, that.specifications) && Objects.equals(number, that.number) && Objects.equals(price, that.price) && Objects.equals(picUrl, that.picUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, productId, goodsName, goodsSn, specifications, number, price, picUrl);
    }

    @Override
    public String toString() {
        return "CartSelectGoodsVO{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", productId=" + productId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSn='" + goodsSn + '\'' +
                ", specifications=" + specifications +
                ", number=" + number +
                ", price=" + price +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}