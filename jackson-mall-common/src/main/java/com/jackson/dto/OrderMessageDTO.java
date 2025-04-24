package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OrderMessageDTO implements Serializable {
    private Long userId;
    private List<Long> goodsIds;

    public OrderMessageDTO() {
    }

    public OrderMessageDTO(Long userId, List<Long> goodsIds) {
        this.userId = userId;
        this.goodsIds = goodsIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<Long> goodsIds) {
        this.goodsIds = goodsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderMessageDTO that = (OrderMessageDTO) o;
        return Objects.equals(userId, that.userId) && Objects.equals(goodsIds, that.goodsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, goodsIds);
    }

    @Override
    public String toString() {
        return "OrderMessageDTO{" +
                "userId=" + userId +
                ", goodsIds=" + goodsIds +
                '}';
    }
}
