package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GoodsSkuVO implements Serializable {
    private List<GoodsSpecificationVO> specsList;
    private List<GoodsProductVO> skuList;

    public GoodsSkuVO() {
    }

    public GoodsSkuVO(List<GoodsSpecificationVO> specsList, List<GoodsProductVO> skuList) {
        this.specsList = specsList;
        this.skuList = skuList;
    }

    public List<GoodsSpecificationVO> getSpecsList() {
        return specsList;
    }

    public void setSpecsList(List<GoodsSpecificationVO> specsList) {
        this.specsList = specsList;
    }

    public List<GoodsProductVO> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<GoodsProductVO> skuList) {
        this.skuList = skuList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodsSkuVO that = (GoodsSkuVO) o;
        return Objects.equals(specsList, that.specsList) && Objects.equals(skuList, that.skuList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specsList, skuList);
    }

    @Override
    public String toString() {
        return "GoodsSkuVO{" +
                "specsList=" + specsList +
                ", skuList=" + skuList +
                '}';
    }
}
