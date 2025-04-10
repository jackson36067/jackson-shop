package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_order_goods")
@EntityListeners(AuditingEntityListener.class)
public class ShopOrderGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("0")
    @Column(name = "goods_id", nullable = false)
    private Long goodsId;

    @ColumnDefault("''")
    @Column(name = "goods_name", nullable = false, length = 127)
    private String goodsName;

    @ColumnDefault("''")
    @Column(name = "goods_sn", nullable = false, length = 63)
    private String goodsSn;

    @ColumnDefault("0")
    @Column(name = "product_id", nullable = false)
    private Long productId;

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
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private ShopOrder shopOrder;

    public ShopOrderGoods() {
    }

    public ShopOrderGoods(Long id, Long goodsId, String goodsName, String goodsSn, Long productId, Short number, BigDecimal price, String specifications, String picUrl, Integer comment, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag, ShopOrder shopOrder) {
        this.id = id;
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
        this.shopOrder = shopOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
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
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrderGoods that = (ShopOrderGoods) o;
        return Objects.equals(id, that.id) && Objects.equals(goodsId, that.goodsId) && Objects.equals(goodsName, that.goodsName) && Objects.equals(goodsSn, that.goodsSn) && Objects.equals(productId, that.productId) && Objects.equals(number, that.number) && Objects.equals(price, that.price) && Objects.equals(specifications, that.specifications) && Objects.equals(picUrl, that.picUrl) && Objects.equals(comment, that.comment) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag) && Objects.equals(shopOrder, that.shopOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, goodsName, goodsSn, productId, number, price, specifications, picUrl, comment, createTime, updateTime, delFlag, shopOrder);
    }

    @Override
    public String toString() {
        return "ShopOrderGoods{" +
                "id=" + id +
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
                ", shopOrder=" + shopOrder +
                '}';
    }
}