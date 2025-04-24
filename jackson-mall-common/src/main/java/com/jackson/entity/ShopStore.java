package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shop_store")
@EntityListeners(AuditingEntityListener.class)
public class ShopStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @ColumnDefault("0")
    @Column(name = "status", nullable = false)
    private Short status;

    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "shopStore")
    private List<ShopGood> shopGoodList;

    @OneToMany(mappedBy = "shopStore")
    private List<ShopCoupon> shopCouponList;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "service_id")
    private Long serviceId;

    public ShopStore() {
    }

    public ShopStore(Long id, String name, Short status, LocalDateTime createTime, LocalDateTime updateTime, List<ShopGood> shopGoodList, List<ShopCoupon> shopCouponList, String avatar, Long serviceId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shopGoodList = shopGoodList;
        this.shopCouponList = shopCouponList;
        this.avatar = avatar;
        this.serviceId = serviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public List<ShopGood> getShopGoodList() {
        return shopGoodList;
    }

    public void setShopGoodList(List<ShopGood> shopGoodList) {
        this.shopGoodList = shopGoodList;
    }

    public List<ShopCoupon> getShopCouponList() {
        return shopCouponList;
    }

    public void setShopCouponList(List<ShopCoupon> shopCouponList) {
        this.shopCouponList = shopCouponList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopStore shopStore = (ShopStore) o;
        return Objects.equals(id, shopStore.id) && Objects.equals(name, shopStore.name) && Objects.equals(status, shopStore.status) && Objects.equals(createTime, shopStore.createTime) && Objects.equals(updateTime, shopStore.updateTime) && Objects.equals(shopGoodList, shopStore.shopGoodList) && Objects.equals(shopCouponList, shopStore.shopCouponList) && Objects.equals(avatar, shopStore.avatar) && Objects.equals(serviceId, shopStore.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, createTime, updateTime, shopGoodList, shopCouponList, avatar, serviceId);
    }

    @Override
    public String toString() {
        return "ShopStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", shopGoodList=" + shopGoodList +
                ", shopCouponList=" + shopCouponList +
                ", avatar='" + avatar + '\'' +
                ", serviceId=" + serviceId +
                '}';
    }
}