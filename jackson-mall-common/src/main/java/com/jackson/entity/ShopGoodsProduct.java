package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 商品sku类
 */
@Entity
@Table(name = "shop_goods_product")
public class ShopGoodsProduct {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "specifications", nullable = false, length = 1023)
    private String specifications; // 规格值列表,Json格式

    @ColumnDefault("0.00")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("0")
    @Column(name = "number", nullable = false)
    private Integer number;

    @ColumnDefault("0")
    @Column(name = "default_selected")
    private Boolean defaultSelected;

    @Column(name = "url", length = 125)
    private String url; // 规格商品图片

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    @ManyToOne
    @JoinColumn(name = "goods_id",referencedColumnName = "id")
    private ShopGood shopGood;

    public ShopGoodsProduct() {
    }

    public ShopGoodsProduct(String specifications, Long id, BigDecimal price, Integer number, Boolean defaultSelected, String url, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag, ShopGood shopGood) {
        this.specifications = specifications;
        this.id = id;
        this.price = price;
        this.number = number;
        this.defaultSelected = defaultSelected;
        this.url = url;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.shopGood = shopGood;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getDefaultSelected() {
        return defaultSelected;
    }

    public void setDefaultSelected(Boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public ShopGood getShopGood() {
        return shopGood;
    }

    public void setShopGood(ShopGood shopGood) {
        this.shopGood = shopGood;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopGoodsProduct that = (ShopGoodsProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(specifications, that.specifications) && Objects.equals(price, that.price) && Objects.equals(number, that.number) && Objects.equals(defaultSelected, that.defaultSelected) && Objects.equals(url, that.url) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag) && Objects.equals(shopGood, that.shopGood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specifications, price, number, defaultSelected, url, createTime, updateTime, delFlag, shopGood);
    }

    @Override
    public String toString() {
        return "ShopGoodsProduct{" +
                "id=" + id +
                ", specifications='" + specifications + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", defaultSelected=" + defaultSelected +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", shopGood=" + shopGood +
                '}';
    }
}