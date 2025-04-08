package com.jackson.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class CartDTO implements Serializable {
    private Long goodsId;
    private String goodsSn;
    private String goodsName;
    private BigDecimal price;
    private Long productId;
    private Short number;
    private Map<String,String> specification;
    private String picUrl;
    private String remark;

    public CartDTO() {
    }

    public CartDTO(String goodsName, Long goodsId, String goodsSn, BigDecimal price, Long productId, Short number, Map<String, String> specification, String picUrl, String remark) {
        this.goodsName = goodsName;
        this.goodsId = goodsId;
        this.goodsSn = goodsSn;
        this.price = price;
        this.productId = productId;
        this.number = number;
        this.specification = specification;
        this.picUrl = picUrl;
        this.remark = remark;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Map<String, String> getSpecification() {
        return specification;
    }

    public void setSpecification(Map<String, String> specification) {
        this.specification = specification;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartDTO cartDTO = (CartDTO) o;
        return Objects.equals(goodsId, cartDTO.goodsId) && Objects.equals(goodsSn, cartDTO.goodsSn) && Objects.equals(goodsName, cartDTO.goodsName) && Objects.equals(price, cartDTO.price) && Objects.equals(productId, cartDTO.productId) && Objects.equals(number, cartDTO.number) && Objects.equals(specification, cartDTO.specification) && Objects.equals(picUrl, cartDTO.picUrl) && Objects.equals(remark, cartDTO.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsSn, goodsName, price, productId, number, specification, picUrl, remark);
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "goodsId=" + goodsId +
                ", goodsSn=" + goodsSn +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", productId=" + productId +
                ", number=" + number +
                ", specification=" + specification +
                ", picUrl='" + picUrl + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
