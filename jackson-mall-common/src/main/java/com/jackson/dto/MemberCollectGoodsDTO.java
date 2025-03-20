package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class MemberCollectGoodsDTO implements Serializable {
    private Long goodsId;
    private Boolean isCollect;

    public MemberCollectGoodsDTO() {
    }

    public MemberCollectGoodsDTO(Boolean isCollect, Long goodsId) {
        this.isCollect = isCollect;
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean collect) {
        isCollect = collect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberCollectGoodsDTO that = (MemberCollectGoodsDTO) o;
        return Objects.equals(goodsId, that.goodsId) && Objects.equals(isCollect, that.isCollect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, isCollect);
    }

    @Override
    public String toString() {
        return "MemberCollectGoodsDTO{" +
                "goodsId=" + goodsId +
                ", isCollect=" + isCollect +
                '}';
    }
}
