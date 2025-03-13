package com.jackson.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shop_channel")
public class ShopChannel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "create_by", length = 32)
    private String createBy;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_by", length = 32)
    private String updateBy;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "code", length = 64)
    private String code;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "remark")
    private String remark;

    @ManyToMany
    @JoinTable(
            name = "shop_channel_good_relation",
            joinColumns = {@JoinColumn(name = "channel_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "good_id", referencedColumnName = "id")}
    )
    private List<ShopGood> shopGoodSet;

    public ShopChannel() {
    }

    public ShopChannel(Integer id, String createBy, LocalDateTime createTime, String updateBy, LocalDateTime updateTime, String code, String name, String remark, List<ShopGood> shopGoodSet) {
        this.id = id;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.code = code;
        this.name = name;
        this.remark = remark;
        this.shopGoodSet = shopGoodSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ShopGood> getShopGoodSet() {
        return shopGoodSet;
    }

    public void setShopGoodSet(List<ShopGood> shopGoodSet) {
        this.shopGoodSet = shopGoodSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopChannel that = (ShopChannel) o;
        return Objects.equals(id, that.id) && Objects.equals(createBy, that.createBy) && Objects.equals(createTime, that.createTime) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateTime, that.updateTime) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(remark, that.remark) && Objects.equals(shopGoodSet, that.shopGoodSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createBy, createTime, updateBy, updateTime, code, name, remark, shopGoodSet);
    }

    @Override
    public String toString() {
        return "ShopChannel{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", shopGoodSet=" + shopGoodSet +
                '}';
    }
}