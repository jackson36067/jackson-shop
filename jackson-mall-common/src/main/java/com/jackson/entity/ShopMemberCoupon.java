package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_member_coupon")
@EntityListeners(AuditingEntityListener.class)
public class ShopMemberCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "coupon_id", nullable = false)
    private Long couponId;

    @ColumnDefault("0")
    @Column(name = "discount", nullable = false)
    private Integer discount;

    @ColumnDefault("0")
    @Column(name = "min", nullable = false)
    private Integer min;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    @ColumnDefault("0")
    @Column(name = "use_status")
    private Short useStatus;

    @Column(name = "used_time")
    private LocalDateTime usedTime;

    @Column(name = "order_id")
    private Long orderId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Short delFlag;

    public ShopMemberCoupon() {
    }

    public ShopMemberCoupon(Long id, Long userId, String title, Long couponId, Integer discount, Integer min, LocalDateTime expireTime, Short useStatus, LocalDateTime usedTime, Long orderId, LocalDateTime createTime, LocalDateTime updateTime, Short delFlag) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.couponId = couponId;
        this.discount = discount;
        this.min = min;
        this.expireTime = expireTime;
        this.useStatus = useStatus;
        this.usedTime = usedTime;
        this.orderId = orderId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
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

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Short getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Short useStatus) {
        this.useStatus = useStatus;
    }

    public LocalDateTime getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(LocalDateTime usedTime) {
        this.usedTime = usedTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopMemberCoupon that = (ShopMemberCoupon) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(title, that.title) && Objects.equals(couponId, that.couponId) && Objects.equals(discount, that.discount) && Objects.equals(min, that.min) && Objects.equals(expireTime, that.expireTime) && Objects.equals(useStatus, that.useStatus) && Objects.equals(usedTime, that.usedTime) && Objects.equals(orderId, that.orderId) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, couponId, discount, min, expireTime, useStatus, usedTime, orderId, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopMemberCoupon{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", couponId=" + couponId +
                ", discount=" + discount +
                ", min=" + min +
                ", expireTime=" + expireTime +
                ", useStatus=" + useStatus +
                ", usedTime=" + usedTime +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}