package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class StoreCouponDTO implements Serializable {
    private Long storeId; // 店铺id
    private Long couponId; // 优惠卷id

    public StoreCouponDTO() {
    }

    public StoreCouponDTO(Long storeId, Long couponId) {
        this.storeId = storeId;
        this.couponId = couponId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreCouponDTO that = (StoreCouponDTO) o;
        return Objects.equals(storeId, that.storeId) && Objects.equals(couponId, that.couponId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, couponId);
    }

    @Override
    public String toString() {
        return "StoreCouponDTO{" +
                "storeId=" + storeId +
                ", couponId=" + couponId +
                '}';
    }
}
