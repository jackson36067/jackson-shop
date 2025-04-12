package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class OrderGoodsVO implements Serializable {
    private Long id;
    private Long goodsId; // 商品id
    private String picUrl; // 商品图片
    private String goodsName; // 商品名称
    private List<String> specifications;
    private Short number;
    private BigDecimal price;
    private Long storeId;
    private String storeName;

    public OrderGoodsVO() {
    }

    public OrderGoodsVO(Long id, Long goodsId, String picUrl, String goodsName, List<String> specifications, Short number, BigDecimal price, Long storeId, String storeName) {
        this.goodsId = goodsId;
        this.id = id;
        this.picUrl = picUrl;
        this.goodsName = goodsName;
        this.specifications = specifications;
        this.number = number;
        this.price = price;
        this.storeId = storeId;
        this.storeName = storeName;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public List<String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<String> specifications) {
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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderGoodsVO that = (OrderGoodsVO) o;
        return Objects.equals(id, that.id) && Objects.equals(goodsId, that.goodsId) && Objects.equals(picUrl, that.picUrl) && Objects.equals(goodsName, that.goodsName) && Objects.equals(specifications, that.specifications) && Objects.equals(number, that.number) && Objects.equals(price, that.price) && Objects.equals(storeId, that.storeId) && Objects.equals(storeName, that.storeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, picUrl, goodsName, specifications, number, price, storeId, storeName);
    }

    @Override
    public String toString() {
        return "OrderGoodsVO{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", picUrl='" + picUrl + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", specifications=" + specifications +
                ", number=" + number +
                ", price=" + price +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}