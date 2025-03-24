package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CollectGoodsVO implements Serializable {
    private Long id; // 收藏商品信息id
    private Long goodsId; // 商品id
    private String name; // 商品名称
    private String picUrl; // 商品图片
    private BigDecimal price; // 商品价格
    private Long storeId;
    private String storeName;
    private Integer collectNumber;

    public CollectGoodsVO() {
    }

    public CollectGoodsVO(Long id, Long goodsId,String name, String picUrl, BigDecimal price, Long storeId, String storeName, Integer collectNumber) {
        this.id = id;
        this.goodsId = goodsId;
        this.name = name;
        this.picUrl = picUrl;
        this.price = price;
        this.storeId = storeId;
        this.storeName = storeName;
        this.collectNumber = collectNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CollectGoodsVO that = (CollectGoodsVO) o;
        return Objects.equals(id, that.id) && Objects.equals(goodsId, that.goodsId) && Objects.equals(name, that.name) && Objects.equals(picUrl, that.picUrl) && Objects.equals(price, that.price) && Objects.equals(storeId, that.storeId) && Objects.equals(storeName, that.storeName) && Objects.equals(collectNumber, that.collectNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, name, picUrl, price, storeId, storeName, collectNumber);
    }

    @Override
    public String toString() {
        return "CollectGoodsVO{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", price='" + price + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", collectNumber=" + collectNumber +
                '}';
    }
}
