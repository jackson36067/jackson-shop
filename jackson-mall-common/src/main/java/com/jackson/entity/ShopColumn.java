package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shop_column")
public class ShopColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 10)
    private String name;

    @Column(name = "sort")
    private Short sort;

    @Column(name = "bg_pic")
    private String bgPic;

    @Column(name = "detail_pic")
    private String detailPic;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToMany
    @JoinTable(
            name = "shop_column_goods_relation",
            joinColumns = {@JoinColumn(name = "column_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id", referencedColumnName = "id")}
    )
    private List<ShopGood> shopGood;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopColumn() {
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

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getBgPic() {
        return bgPic;
    }

    public void setBgPic(String bgPic) {
        this.bgPic = bgPic;
    }

    public String getDetailPic() {
        return detailPic;
    }

    public void setDetailPic(String detailPic) {
        this.detailPic = detailPic;
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

    public List<ShopGood> getShopGood() {
        return shopGood;
    }

    public void setShopGood(List<ShopGood> shopGood) {
        this.shopGood = shopGood;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopColumn that = (ShopColumn) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(sort, that.sort) && Objects.equals(bgPic, that.bgPic) && Objects.equals(detailPic, that.detailPic) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(shopGood, that.shopGood) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sort, bgPic, detailPic, createTime, updateTime, shopGood, delFlag);
    }

    public ShopColumn(Long id, String name, Short sort, String bgPic, String detailPic, LocalDateTime createTime, LocalDateTime updateTime, List<ShopGood> shopGood, Boolean delFlag) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.bgPic = bgPic;
        this.detailPic = detailPic;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shopGood = shopGood;
        this.delFlag = delFlag;
    }
}