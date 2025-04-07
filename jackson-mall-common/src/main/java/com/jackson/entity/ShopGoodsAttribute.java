package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_goods_attribute")
public class ShopGoodsAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

//    @ColumnDefault("0")
//    @Column(name = "goods_id", nullable = false)
//    private Long goodsId;

    @Column(name = "attribute", nullable = false)
    private String attribute;

    @Column(name = "value", nullable = false)
    private String value;

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

    public ShopGoodsAttribute() {
    }

    public ShopGoodsAttribute(Integer id, String attribute, String value, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag,ShopGood shopGood) {
        this.id = id;
        this.attribute = attribute;
        this.value = value;
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


    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        ShopGoodsAttribute that = (ShopGoodsAttribute) o;
        return Objects.equals(id, that.id) && Objects.equals(attribute, that.attribute) && Objects.equals(value, that.value) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag) && Objects.equals(shopGood, that.shopGood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attribute, value, createTime, updateTime, delFlag, shopGood);
    }

    @Override
    public String toString() {
        return "ShopGoodsAttribute{" +
                "id=" + id +
                ", attribute='" + attribute + '\'' +
                ", value='" + value + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", shopGood=" + shopGood +
                '}';
    }
}