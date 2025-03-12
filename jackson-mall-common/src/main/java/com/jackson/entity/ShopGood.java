package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_goods")
public class ShopGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("''")
    @Column(name = "goods_sn", nullable = false, length = 63)
    private String goodsSn;

    @ColumnDefault("''")
    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private ShopCategory shopCategory;

    @ColumnDefault("0")
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "gallery", length = 1023)
    private String gallery;

    @ColumnDefault("''")
    @Column(name = "keywords")
    private String keywords;

    @ColumnDefault("''")
    @Column(name = "brief")
    private String brief;

    @ColumnDefault("1")
    @Column(name = "is_on_sale")
    private Boolean isOnSale;

    @ColumnDefault("100")
    @Column(name = "sort")
    private Integer sort;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "share_url")
    private String shareUrl;

    @ColumnDefault("0")
    @Column(name = "is_new")
    private Boolean isNew;

    @ColumnDefault("0")
    @Column(name = "is_hot")
    private Boolean isHot;

    @ColumnDefault("'’件‘'")
    @Column(name = "unit", length = 31)
    private String unit;

    @ColumnDefault("0.00")
    @Column(name = "counter_price", precision = 10, scale = 2)
    private BigDecimal counterPrice;

    @ColumnDefault("100000.00")
    @Column(name = "retail_price", precision = 10, scale = 2)
    private BigDecimal retailPrice;

    @ColumnDefault("0")
    @Column(name = "actual_sales")
    private Integer actualSales;

    @ColumnDefault("0")
    @Column(name = "virtual_sales")
    private Integer virtualSales;

    @Lob
    @Column(name = "detail")
    private String detail;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopGood() {
    }

    public ShopGood(Long id, String goodsSn, String name, ShopCategory shopCategory, Long brandId, String gallery, String keywords, String brief, Boolean isOnSale, Integer sort, String picUrl, String shareUrl, Boolean isNew, Boolean isHot, String unit, BigDecimal counterPrice, BigDecimal retailPrice, Integer actualSales, Integer virtualSales, String detail, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
        this.id = id;
        this.goodsSn = goodsSn;
        this.name = name;
        this.shopCategory = shopCategory;
        this.brandId = brandId;
        this.gallery = gallery;
        this.keywords = keywords;
        this.brief = brief;
        this.isOnSale = isOnSale;
        this.sort = sort;
        this.picUrl = picUrl;
        this.shareUrl = shareUrl;
        this.isNew = isNew;
        this.isHot = isHot;
        this.unit = unit;
        this.counterPrice = counterPrice;
        this.retailPrice = retailPrice;
        this.actualSales = actualSales;
        this.virtualSales = virtualSales;
        this.detail = detail;
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

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean hot) {
        isHot = hot;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getActualSales() {
        return actualSales;
    }

    public void setActualSales(Integer actualSales) {
        this.actualSales = actualSales;
    }

    public Integer getVirtualSales() {
        return virtualSales;
    }

    public void setVirtualSales(Integer virtualSales) {
        this.virtualSales = virtualSales;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        ShopGood shopGood = (ShopGood) o;
        return Objects.equals(id, shopGood.id) && Objects.equals(goodsSn, shopGood.goodsSn) && Objects.equals(name, shopGood.name) && Objects.equals(shopCategory, shopGood.shopCategory) && Objects.equals(brandId, shopGood.brandId) && Objects.equals(gallery, shopGood.gallery) && Objects.equals(keywords, shopGood.keywords) && Objects.equals(brief, shopGood.brief) && Objects.equals(isOnSale, shopGood.isOnSale) && Objects.equals(sort, shopGood.sort) && Objects.equals(picUrl, shopGood.picUrl) && Objects.equals(shareUrl, shopGood.shareUrl) && Objects.equals(isNew, shopGood.isNew) && Objects.equals(isHot, shopGood.isHot) && Objects.equals(unit, shopGood.unit) && Objects.equals(counterPrice, shopGood.counterPrice) && Objects.equals(retailPrice, shopGood.retailPrice) && Objects.equals(actualSales, shopGood.actualSales) && Objects.equals(virtualSales, shopGood.virtualSales) && Objects.equals(detail, shopGood.detail) && Objects.equals(createTime, shopGood.createTime) && Objects.equals(updateTime, shopGood.updateTime) && Objects.equals(delFlag, shopGood.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsSn, name, shopCategory, brandId, gallery, keywords, brief, isOnSale, sort, picUrl, shareUrl, isNew, isHot, unit, counterPrice, retailPrice, actualSales, virtualSales, detail, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopGood{" +
                "id=" + id +
                ", goodsSn='" + goodsSn + '\'' +
                ", name='" + name + '\'' +
                ", shopCategory=" + shopCategory +
                ", brandId=" + brandId +
                ", gallery='" + gallery + '\'' +
                ", keywords='" + keywords + '\'' +
                ", brief='" + brief + '\'' +
                ", isOnSale=" + isOnSale +
                ", sort=" + sort +
                ", picUrl='" + picUrl + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", unit='" + unit + '\'' +
                ", counterPrice=" + counterPrice +
                ", retailPrice=" + retailPrice +
                ", actualSales=" + actualSales +
                ", virtualSales=" + virtualSales +
                ", detail='" + detail + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}