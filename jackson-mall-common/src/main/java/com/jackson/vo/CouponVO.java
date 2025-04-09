package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class CouponVO implements Serializable {
    private Long id;
    private String title;
    private Integer discount;
    private Integer min; // 最低多少使用优惠卷价格
    private Integer expireDay;
    private Long storeId;

    public CouponVO() {
    }

    public CouponVO(Long id, String title, Integer discount, Integer min, Integer expireDay, Long storeId) {
        this.id = id;
        this.title = title;
        this.discount = discount;
        this.min = min;
        this.expireDay = expireDay;
        this.storeId = storeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Integer expireDay) {
        this.expireDay = expireDay;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CouponVO couponVO = (CouponVO) o;
        return Objects.equals(id, couponVO.id) && Objects.equals(title, couponVO.title) && Objects.equals(discount, couponVO.discount) && Objects.equals(min, couponVO.min) && Objects.equals(expireDay, couponVO.expireDay) && Objects.equals(storeId, couponVO.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, discount, min, expireDay, storeId);
    }

    @Override
    public String toString() {
        return "CouponVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", discount=" + discount +
                ", min=" + min +
                ", expireDay=" + expireDay +
                ", storeId=" + storeId +
                '}';
    }
}