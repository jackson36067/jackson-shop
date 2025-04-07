package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GoodsSpecificationVO implements Serializable {
    private String name; // 商品规格名称
    private List<GoodsSpecificationValueVO> options; // 规格可选值(包含名字以及图片)

    public GoodsSpecificationVO() {
    }

    public GoodsSpecificationVO(String specification, List<GoodsSpecificationValueVO> options) {
        this.name = specification;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsSpecificationValueVO> getOptions() {
        return options;
    }

    public void setOptions(List<GoodsSpecificationValueVO> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodsSpecificationVO that = (GoodsSpecificationVO) o;
        return Objects.equals(name, that.name) && Objects.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, options);
    }

    @Override
    public String toString() {
        return "GoodsSpecificationVO{" +
                "name='" + name + '\'' +
                ", options=" + options +
                '}';
    }
}