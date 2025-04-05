package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GoodsDetailVO implements Serializable {
    private Long id;
    private Long storeId;
    private String name;
    private String brief;
    private List<String> gallery;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private String detail; // 商品详情
    private Boolean isCollect; // 用户是否收藏该商品
    private List<GoodsCommentVO> goodsCommentVOList; // 该商品的评论列表
    private Integer goodCommentNumber; // 好评数量
    private Integer naturalCommentNumber; // 中评数量
    private Integer badCommentNumber; // 差评数量
    private Integer hasPictureCommentNumber; // 有图评论数量
    private String defaultAddress;

    public GoodsDetailVO() {
    }

    public GoodsDetailVO(String name, Long id, Long shopId, String brief, List<String> gallery, BigDecimal counterPrice, BigDecimal retailPrice, String detail, Boolean isCollect, List<GoodsCommentVO> goodsCommentVOList, Integer goodCommentNumber, Integer naturalCommentNumber, Integer badCommentNumber, Integer hasPictureCommentNumber, String defaultAddress) {
        this.name = name;
        this.id = id;
        this.storeId = shopId;
        this.brief = brief;
        this.gallery = gallery;
        this.counterPrice = counterPrice;
        this.retailPrice = retailPrice;
        this.detail = detail;
        this.isCollect = isCollect;
        this.goodsCommentVOList = goodsCommentVOList;
        this.goodCommentNumber = goodCommentNumber;
        this.naturalCommentNumber = naturalCommentNumber;
        this.badCommentNumber = badCommentNumber;
        this.hasPictureCommentNumber = hasPictureCommentNumber;
        this.defaultAddress = defaultAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean collect) {
        isCollect = collect;
    }

    public Integer getGoodCommentNumber() {
        return goodCommentNumber;
    }

    public void setGoodCommentNumber(Integer goodCommentNumber) {
        this.goodCommentNumber = goodCommentNumber;
    }

    public Integer getNaturalCommentNumber() {
        return naturalCommentNumber;
    }

    public void setNaturalCommentNumber(Integer naturalCommentNumber) {
        this.naturalCommentNumber = naturalCommentNumber;
    }

    public Integer getBadCommentNumber() {
        return badCommentNumber;
    }

    public void setBadCommentNumber(Integer badCommentNumber) {
        this.badCommentNumber = badCommentNumber;
    }

    public Integer getHasPictureCommentNumber() {
        return hasPictureCommentNumber;
    }

    public void setHasPictureCommentNumber(Integer hasPictureCommentNumber) {
        this.hasPictureCommentNumber = hasPictureCommentNumber;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public List<GoodsCommentVO> getGoodsCommentVOList() {
        return goodsCommentVOList;
    }

    public void setGoodsCommentVOList(List<GoodsCommentVO> goodsCommentVOList) {
        this.goodsCommentVOList = goodsCommentVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDetailVO that = (GoodsDetailVO) o;
        return Objects.equals(id, that.id) && Objects.equals(storeId, that.storeId) && Objects.equals(name, that.name) && Objects.equals(brief, that.brief) && Objects.equals(gallery, that.gallery) && Objects.equals(counterPrice, that.counterPrice) && Objects.equals(retailPrice, that.retailPrice) && Objects.equals(detail, that.detail) && Objects.equals(isCollect, that.isCollect) && Objects.equals(goodsCommentVOList, that.goodsCommentVOList) && Objects.equals(goodCommentNumber, that.goodCommentNumber) && Objects.equals(naturalCommentNumber, that.naturalCommentNumber) && Objects.equals(badCommentNumber, that.badCommentNumber) && Objects.equals(hasPictureCommentNumber, that.hasPictureCommentNumber) && Objects.equals(defaultAddress, that.defaultAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeId, name, brief, gallery, counterPrice, retailPrice, detail, isCollect, goodsCommentVOList, goodCommentNumber, naturalCommentNumber, badCommentNumber, hasPictureCommentNumber, defaultAddress);
    }

    @Override
    public String toString() {
        return "GoodsDetailVO{" +
                "id=" + id +
                ", shopId=" + storeId +
                ", name='" + name + '\'' +
                ", brief='" + brief + '\'' +
                ", gallery=" + gallery +
                ", counterPrice=" + counterPrice +
                ", retailPrice=" + retailPrice +
                ", detail='" + detail + '\'' +
                ", isCollect=" + isCollect +
                ", goodsCommentVOList=" + goodsCommentVOList +
                ", goodCommentNumber=" + goodCommentNumber +
                ", naturalCommentNumber=" + naturalCommentNumber +
                ", badCommentNumber=" + badCommentNumber +
                ", hasPictureCommentNumber=" + hasPictureCommentNumber +
                ", defaultAddress='" + defaultAddress + '\'' +
                '}';
    }
}