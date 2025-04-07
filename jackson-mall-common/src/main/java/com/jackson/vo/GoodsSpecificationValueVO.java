package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class GoodsSpecificationValueVO implements Serializable {
    private String value;
    private String picUrl;

    public GoodsSpecificationValueVO() {
    }

    public GoodsSpecificationValueVO(String value, String picUrl) {
        this.value = value;
        this.picUrl = picUrl;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        GoodsSpecificationValueVO that = (GoodsSpecificationValueVO) o;
        return Objects.equals(value, that.value) && Objects.equals(picUrl, that.picUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, picUrl);
    }

    @Override
    public String toString() {
        return "GoodsSpecificationValueVO{" +
                "value='" + value + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
