package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_cart")
@EntityListeners(AuditingEntityListener.class)
public class ShopCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "goods_sn", length = 63)
    private String goodsSn;

    @Column(name = "goods_name", length = 127)
    private String goodsName;

    @Column(name = "product_id")
    private Long productId;

    @ColumnDefault("0.00")
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("0")
    @Column(name = "number")
    private Short number;

    @Column(name = "specifications", length = 1023)
    private String specifications;

    @ColumnDefault("1")
    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "remark", length = 500)
    private String remark;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopCart() {
    }

    public ShopCart(Long id, Long userId, Long goodsId, String goodsSn, String goodsName, Long productId, BigDecimal price, Short number, String specifications, Boolean checked, String picUrl, String remark, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCart shopCart = (ShopCart) o;
        return Objects.equals(id, shopCart.id) && Objects.equals(userId, shopCart.userId) && Objects.equals(goodsId, shopCart.goodsId) && Objects.equals(goodsSn, shopCart.goodsSn) && Objects.equals(goodsName, shopCart.goodsName) && Objects.equals(productId, shopCart.productId) && Objects.equals(price, shopCart.price) && Objects.equals(number, shopCart.number) && Objects.equals(specifications, shopCart.specifications) && Objects.equals(checked, shopCart.checked) && Objects.equals(picUrl, shopCart.picUrl) && Objects.equals(remark, shopCart.remark) && Objects.equals(createTime, shopCart.createTime) && Objects.equals(updateTime, shopCart.updateTime) && Objects.equals(delFlag, shopCart.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, goodsId, goodsSn, goodsName, productId, price, number, specifications, checked, picUrl, remark, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopCart{" +
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
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}