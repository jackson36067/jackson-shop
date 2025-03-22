package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class MemberCouponItemVO implements Serializable {
    private Long id;
    private Integer discount;
    private String title;
    private LocalDateTime expireTime;
    private Integer min;

    public MemberCouponItemVO() {
    }

    public MemberCouponItemVO(Long id, Integer discount, String title, LocalDateTime expireTime, Integer min) {
        this.id = id;
        this.discount = discount;
        this.title = title;
        this.expireTime = expireTime;
        this.min = min;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MemberCouponItemVO that = (MemberCouponItemVO) o;
        return Objects.equals(id, that.id) && Objects.equals(discount, that.discount) && Objects.equals(title, that.title) && Objects.equals(expireTime, that.expireTime) && Objects.equals(min, that.min);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discount, title, expireTime, min);
    }

    @Override
    public String toString() {
        return "MemberCouponItemVO{" +
                "id=" + id +
                ", discount=" + discount +
                ", title='" + title + '\'' +
                ", expireTime=" + expireTime +
                ", min=" + min +
                '}';
    }
}
