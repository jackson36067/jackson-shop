package com.jackson.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class GoodsHistoryVO {
    private Long id; // 商品id
    private Long browseId; // 用户浏览记录id
    private String goodsName;
    private BigDecimal price;
    private String picUrl;

    public GoodsHistoryVO() {
    }

    public GoodsHistoryVO(Long id, Long browseId, String goodsName, BigDecimal price, String picUrl) {
        this.id = id;
        this.browseId = browseId;
        this.goodsName = goodsName;
        this.price = price;
        this.picUrl = picUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrowseId() {
        return browseId;
    }

    public void setBrowseId(Long browseId) {
        this.browseId = browseId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodsHistoryVO that = (GoodsHistoryVO) o;
        return Objects.equals(id, that.id) && Objects.equals(browseId, that.browseId) && Objects.equals(goodsName, that.goodsName) && Objects.equals(price, that.price) && Objects.equals(picUrl, that.picUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, browseId, goodsName, price, picUrl);
    }

    @Override
    public String toString() {
        return "GoodsHistoryVO{" +
                "id=" + id +
                ", browseId=" + browseId +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
