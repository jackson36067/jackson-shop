package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_category")
public class ShopCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("''")
    @Column(name = "name", nullable = false, length = 63)
    private String name;

    @ColumnDefault("''")
    @Column(name = "keywords", nullable = false, length = 1023)
    private String keywords;

    @ColumnDefault("''")
    @Column(name = "remark")
    private String remark;

    @ColumnDefault("0")
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @ColumnDefault("''")
    @Column(name = "icon_url")
    private String iconUrl;

    @ColumnDefault("''")
    @Column(name = "pic_url")
    private String picUrl;

    @ColumnDefault("'L1'")
    @Column(name = "level")
    private String level;

    @ColumnDefault("50")
    @Column(name = "sort")
    private Integer sort;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopCategory() {
    }

    public ShopCategory(Long id, String name, String keywords, String remark, Integer pid, String iconUrl, String picUrl, String level, Integer sort, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
        this.id = id;
        this.name = name;
        this.keywords = keywords;
        this.remark = remark;
        this.pid = pid;
        this.iconUrl = iconUrl;
        this.picUrl = picUrl;
        this.level = level;
        this.sort = sort;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCategory that = (ShopCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(keywords, that.keywords) && Objects.equals(remark, that.remark) && Objects.equals(pid, that.pid) && Objects.equals(iconUrl, that.iconUrl) && Objects.equals(picUrl, that.picUrl) && Objects.equals(level, that.level) && Objects.equals(sort, that.sort) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, keywords, remark, pid, iconUrl, picUrl, level, sort, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", remark='" + remark + '\'' +
                ", pid=" + pid +
                ", iconUrl='" + iconUrl + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", level='" + level + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}