package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_goods_specification")
public class ShopGoodsSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("''")
    @Column(name = "specification", nullable = false)
    private String specification;

    @ColumnDefault("''")
    @Column(name = "value", nullable = false)
    private String value;

    @ColumnDefault("''")
    @Column(name = "pic_url", nullable = false)
    private String picUrl;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    @ManyToOne
    @JoinColumn(name = "goods_id",referencedColumnName = "id")
    private ShopGood shopGood;

    public ShopGoodsSpecification() {
    }

    public ShopGoodsSpecification(String specification, Integer id, String value, String picUrl, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag, ShopGood shopGood) {
        this.specification = specification;
        this.id = id;
        this.value = value;
        this.picUrl = picUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.shopGood = shopGood;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public ShopGood getShopGood() {
        return shopGood;
    }

    public void setShopGood(ShopGood shopGood) {
        this.shopGood = shopGood;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopGoodsSpecification that = (ShopGoodsSpecification) o;
        return Objects.equals(id, that.id) && Objects.equals(specification, that.specification) && Objects.equals(value, that.value) && Objects.equals(picUrl, that.picUrl) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag) && Objects.equals(shopGood, that.shopGood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specification, value, picUrl, createTime, updateTime, delFlag, shopGood);
    }

    @Override
    public String toString() {
        return "ShopGoodsSpecification{" +
                "id=" + id +
                ", specification='" + specification + '\'' +
                ", value='" + value + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", shopGood=" + shopGood +
                '}';
    }
}