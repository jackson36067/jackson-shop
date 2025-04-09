package com.jackson.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GoodsDetailVO implements Serializable {
    private Long id;
    private String goodsSn;
    private Long storeId;
    private String name;
    private String brief;
    private List<String> gallery;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private String detail; // 商品详情
    private Boolean isCollect; // 用户是否收藏该商品
    private List<GoodsCommentVO> goodsCommentVOList; // 该商品的评论列表
    private Integer totalCommentNumber; // 商品总评论数量
    private Integer goodCommentNumber; // 好评数量
    private Integer naturalCommentNumber; // 中评数量
    private Integer badCommentNumber; // 差评数量
    private Integer hasPictureCommentNumber; // 有图评论数量
    private Long defaultAddressId;
    private String defaultAddress; // 默认收货地址
    private String tel; // 默认收货电话号码
    private String consignee; // 默认收货人
    private List<GoodsAttributeVO> goodsAttributeList;
    private List<GoodsSpecificationVO> goodsSpecificationList;
    private List<GoodsProductVO> goodsProductList;

    public GoodsDetailVO() {
    }

    public GoodsDetailVO(String name, String goodsSn, Long id, Long shopId, String brief, List<String> gallery, BigDecimal counterPrice, BigDecimal retailPrice, String detail, Boolean isCollect, List<GoodsCommentVO> goodsCommentVOList, Integer totalCommentNumber, Integer goodCommentNumber, Integer naturalCommentNumber, Integer badCommentNumber, Integer hasPictureCommentNumber, Long defaultAddressId, String defaultAddress, String tel, String consignee, List<GoodsAttributeVO> goodsAttributeList, List<GoodsSpecificationVO> goodsSpecificationList, List<GoodsProductVO> goodsProductList) {
        this.name = name;
        this.id = id;
        this.goodsSn = goodsSn;
        this.storeId = shopId;
        this.brief = brief;
        this.gallery = gallery;
        this.counterPrice = counterPrice;
        this.retailPrice = retailPrice;
        this.detail = detail;
        this.isCollect = isCollect;
        this.goodsCommentVOList = goodsCommentVOList;
        this.totalCommentNumber = totalCommentNumber;
        this.goodCommentNumber = goodCommentNumber;
        this.naturalCommentNumber = naturalCommentNumber;
        this.badCommentNumber = badCommentNumber;
        this.hasPictureCommentNumber = hasPictureCommentNumber;
        this.defaultAddressId = defaultAddressId;
        this.defaultAddress = defaultAddress;
        this.tel = tel;
        this.consignee = consignee;
        this.goodsAttributeList = goodsAttributeList;
        this.goodsSpecificationList = goodsSpecificationList;
        this.goodsProductList = goodsProductList;
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

    public Integer getTotalCommentNumber() {
        return totalCommentNumber;
    }

    public void setTotalCommentNumber(Integer totalCommentNumber) {
        this.totalCommentNumber = totalCommentNumber;
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

    public Long getDefaultAddressId() {
        return defaultAddressId;
    }

    public void setDefaultAddressId(Long defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public List<GoodsCommentVO> getGoodsCommentVOList() {
        return goodsCommentVOList;
    }

    public void setGoodsCommentVOList(List<GoodsCommentVO> goodsCommentVOList) {
        this.goodsCommentVOList = goodsCommentVOList;
    }

    public List<GoodsAttributeVO> getGoodsAttributeList() {
        return goodsAttributeList;
    }

    public void setGoodsAttributeList(List<GoodsAttributeVO> goodsAttributeList) {
        this.goodsAttributeList = goodsAttributeList;
    }

    public List<GoodsSpecificationVO> getGoodsSpecificationList() {
        return goodsSpecificationList;
    }

    public void setGoodsSpecificationList(List<GoodsSpecificationVO> goodsSpecificationList) {
        this.goodsSpecificationList = goodsSpecificationList;
    }

    public List<GoodsProductVO> getGoodsProductList() {
        return goodsProductList;
    }

    public void setGoodsProductList(List<GoodsProductVO> goodsProductList) {
        this.goodsProductList = goodsProductList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDetailVO that = (GoodsDetailVO) o;
        return Objects.equals(id, that.id) && Objects.equals(goodsSn, that.goodsSn) && Objects.equals(storeId, that.storeId) && Objects.equals(name, that.name) && Objects.equals(brief, that.brief) && Objects.equals(gallery, that.gallery) && Objects.equals(counterPrice, that.counterPrice) && Objects.equals(retailPrice, that.retailPrice) && Objects.equals(detail, that.detail) && Objects.equals(isCollect, that.isCollect) && Objects.equals(goodsCommentVOList, that.goodsCommentVOList) && Objects.equals(totalCommentNumber, that.totalCommentNumber) && Objects.equals(goodCommentNumber, that.goodCommentNumber) && Objects.equals(naturalCommentNumber, that.naturalCommentNumber) && Objects.equals(badCommentNumber, that.badCommentNumber) && Objects.equals(hasPictureCommentNumber, that.hasPictureCommentNumber) && Objects.equals(defaultAddressId, that.defaultAddressId) && Objects.equals(defaultAddress, that.defaultAddress) && Objects.equals(tel, that.tel) && Objects.equals(consignee, that.consignee) && Objects.equals(goodsAttributeList, that.goodsAttributeList) && Objects.equals(goodsSpecificationList, that.goodsSpecificationList) && Objects.equals(goodsProductList, that.goodsProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsSn, storeId, name, brief, gallery, counterPrice, retailPrice, detail, isCollect, goodsCommentVOList, totalCommentNumber, goodCommentNumber, naturalCommentNumber, badCommentNumber, hasPictureCommentNumber, defaultAddressId, defaultAddress, tel, consignee, goodsAttributeList, goodsSpecificationList, goodsProductList);
    }

    @Override
    public String toString() {
        return "GoodsDetailVO{" +
                "id=" + id +
                ", goodsSn='" + goodsSn + '\'' +
                ", storeId=" + storeId +
                ", name='" + name + '\'' +
                ", brief='" + brief + '\'' +
                ", gallery=" + gallery +
                ", counterPrice=" + counterPrice +
                ", retailPrice=" + retailPrice +
                ", detail='" + detail + '\'' +
                ", isCollect=" + isCollect +
                ", goodsCommentVOList=" + goodsCommentVOList +
                ", totalCommentNumber=" + totalCommentNumber +
                ", goodCommentNumber=" + goodCommentNumber +
                ", naturalCommentNumber=" + naturalCommentNumber +
                ", badCommentNumber=" + badCommentNumber +
                ", hasPictureCommentNumber=" + hasPictureCommentNumber +
                ", defaultAddressId=" + defaultAddressId +
                ", defaultAddress='" + defaultAddress + '\'' +
                ", tel='" + tel + '\'' +
                ", consignee='" + consignee + '\'' +
                ", goodsAttributeList=" + goodsAttributeList +
                ", goodsSpecificationList=" + goodsSpecificationList +
                ", goodsProductList=" + goodsProductList +
                '}';
    }
}