package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MemberCollectGoodsDTO implements Serializable {
    private Long goodsId;
    private String goodsName;
    private Boolean isCollect;
    private List<Long> idList; // 用户收藏商品id

    public MemberCollectGoodsDTO() {
    }

    public MemberCollectGoodsDTO(Boolean isCollect, String goodsName, Long goodsId, List<Long> idList) {
        this.isCollect = isCollect;
        this.goodsName = goodsName;
        this.goodsId = goodsId;
        this.idList = idList;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean collect) {
        isCollect = collect;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MemberCollectGoodsDTO that = (MemberCollectGoodsDTO) o;
        return Objects.equals(goodsId, that.goodsId) && Objects.equals(goodsName, that.goodsName) && Objects.equals(isCollect, that.isCollect) && Objects.equals(idList, that.idList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, isCollect, idList);
    }

    @Override
    public String toString() {
        return "MemberCollectGoodsDTO{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", isCollect=" + isCollect +
                ", idList=" + idList +
                '}';
    }
}
