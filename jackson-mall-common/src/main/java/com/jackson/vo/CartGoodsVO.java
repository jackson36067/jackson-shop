package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CartGoodsVO implements Serializable {
    private Long id;
    private Long userId;
    private Long goodsId;
    private String goodsSn;
    private String goodsName;
    private Long productId; // 商品skuId
    private BigDecimal price;
    private Short number;
    private String specifications; // 商品规格列表,初始数据为一个json数据
    private Boolean checked;
    private String picUrl;
    private String remark;
    private Long storeId;
    private String storeName;
    private Boolean isContainCoupon; // 商家是否给该商品提供优惠卷
    private Boolean isCollect; // 判断用户是否收藏了商品

    public CartGoodsVO() {
    }

    public CartGoodsVO(Long id, Long userId, Long goodsId, String goodsSn, String goodsName, Long productId, BigDecimal price, Short number, String specifications, Boolean checked, String picUrl, String remark, Long storeId, String storeName, Boolean isContainCoupon, Boolean isCollect) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsSn = goodsSn;
        this.goodsName = goodsName;
        this.productId = productId;
        this.price = price;
        this.number = number;
        this.specifications = specifications;
        this.checked = checked;
        this.picUrl = picUrl;
        this.remark = remark;
        this.storeId = storeId;
        this.storeName = storeName;
        this.isContainCoupon = isContainCoupon;
        this.isCollect = isCollect;
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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Boolean getIsContainCoupon() {
        return isContainCoupon;
    }

    public void setIsContainCoupon(Boolean containCoupon) {
        isContainCoupon = containCoupon;
    }

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean collect) {
        isCollect = collect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartGoodsVO that = (CartGoodsVO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(goodsId, that.goodsId) && Objects.equals(goodsSn, that.goodsSn) && Objects.equals(goodsName, that.goodsName) && Objects.equals(productId, that.productId) && Objects.equals(price, that.price) && Objects.equals(number, that.number) && Objects.equals(specifications, that.specifications) && Objects.equals(checked, that.checked) && Objects.equals(picUrl, that.picUrl) && Objects.equals(remark, that.remark) && Objects.equals(storeId, that.storeId) && Objects.equals(storeName, that.storeName) && Objects.equals(isContainCoupon, that.isContainCoupon) && Objects.equals(isCollect, that.isCollect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, goodsId, goodsSn, goodsName, productId, price, number, specifications, checked, picUrl, remark, storeId, storeName, isContainCoupon, isCollect);
    }

    @Override
    public String toString() {
        return "CartGoodsVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", goodsSn='" + goodsSn + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", productId=" + productId +
                ", price=" + price +
                ", number=" + number +
                ", specifications='" + specifications + '\'' +
                ", checked=" + checked +
                ", picUrl='" + picUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", isContainCoupon=" + isContainCoupon +
                ", isCollect=" + isCollect +
                '}';
    }
}
