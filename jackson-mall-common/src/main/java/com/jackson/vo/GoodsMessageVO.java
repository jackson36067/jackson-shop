package com.jackson.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class GoodsMessageVO {
    private Long id;
    private String goodsSn;
    private String name;
    private String brief;
    private String picUrl;
    private Boolean isNew;
    private Boolean isHot;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;

    public GoodsMessageVO() {
    }

    public GoodsMessageVO(Long id, String goodsSn, String name, String brief, String picUrl, Boolean isNew, Boolean isHot, BigDecimal counterPrice, BigDecimal retailPrice) {
        this.id = id;
        this.goodsSn = goodsSn;
        this.name = name;
        this.brief = brief;
        this.picUrl = picUrl;
        this.isNew = isNew;
        this.isHot = isHot;
        this.counterPrice = counterPrice;
        this.retailPrice = retailPrice;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsMessageVO that = (GoodsMessageVO) o;
        return Objects.equals(id, that.id) && Objects.equals(goodsSn, that.goodsSn) && Objects.equals(name, that.name) && Objects.equals(brief, that.brief) && Objects.equals(picUrl, that.picUrl) && Objects.equals(isNew, that.isNew) && Objects.equals(isHot, that.isHot) && Objects.equals(counterPrice, that.counterPrice) && Objects.equals(retailPrice, that.retailPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsSn, name, brief, picUrl, isNew, isHot, counterPrice, retailPrice);
    }

    @Override
    public String toString() {
        return "GoodsMessageVO{" +
                "id=" + id +
                ", goodsSn='" + goodsSn + '\'' +
                ", name='" + name + '\'' +
                ", brief='" + brief + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", counterPrice=" + counterPrice +
                ", retailPrice=" + retailPrice +
                '}';
    }
}
