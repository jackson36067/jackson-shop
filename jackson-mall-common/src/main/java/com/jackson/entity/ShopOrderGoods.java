package com.jackson.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "shop_order_goods")
public class ShopOrderGoods {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("0")
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @ColumnDefault("0")
    @Column(name = "goods_id", nullable = false)
    private Integer goodsId;

    @ColumnDefault("''")
    @Column(name = "goods_name", nullable = false, length = 127)
    private String goodsName;

    @ColumnDefault("''")
    @Column(name = "goods_sn", nullable = false, length = 63)
    private String goodsSn;

    @ColumnDefault("0")
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @ColumnDefault("0")
    @Column(name = "number", nullable = false)
    private Short number;

    @ColumnDefault("0.00")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "specifications", nullable = false, length = 1023)
    private String specifications;

    @ColumnDefault("''")
    @Column(name = "pic_url", nullable = false)
    private String picUrl;

    @ColumnDefault("0")
    @Column(name = "comment")
    private Integer comment;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopOrderGoods() {
    }

    public ShopOrderGoods(Long id, Integer orderId, Integer goodsId, String goodsName, String goodsSn, Integer productId, Short number, BigDecimal price, String specifications, String picUrl, Integer comment, Instant createTime, Instant updateTime, Boolean delFlag) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsSn = goodsSn;
        this.productId = productId;
        this.number = number;
        this.price = price;
        this.specifications = specifications;
        this.picUrl = picUrl;
        this.comment = comment;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
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
        ShopOrderGoods that = (ShopOrderGoods) o;
        return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(goodsId, that.goodsId) && Objects.equals(goodsName, that.goodsName) && Objects.equals(goodsSn, that.goodsSn) && Objects.equals(productId, that.productId) && Objects.equals(number, that.number) && Objects.equals(price, that.price) && Objects.equals(specifications, that.specifications) && Objects.equals(picUrl, that.picUrl) && Objects.equals(comment, that.comment) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, goodsId, goodsName, goodsSn, productId, number, price, specifications, picUrl, comment, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopOrderGoods{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSn='" + goodsSn + '\'' +
                ", productId=" + productId +
                ", number=" + number +
                ", price=" + price +
                ", specifications='" + specifications + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", comment=" + comment +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}
