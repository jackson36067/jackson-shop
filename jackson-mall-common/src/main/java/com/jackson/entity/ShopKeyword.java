package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_keyword")
public class ShopKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("''")
    @Column(name = "keyword", nullable = false, length = 127)
    private String keyword;

    @Column(name = "jump_type")
    private Boolean jumpType;

    @ColumnDefault("''")
    @Column(name = "url", nullable = false)
    private String url;

    @ColumnDefault("0")
    @Column(name = "is_hot", nullable = false)
    private Boolean isHot = false;

    @ColumnDefault("0")
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    @ColumnDefault("100")
    @Column(name = "sort", nullable = false)
    private Integer sort;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopKeyword() {
    }

    public ShopKeyword(Long id, String keyword, Boolean jumpType, String url, Boolean isHot, Boolean isDefault, Integer sort, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
        this.id = id;
        this.keyword = keyword;
        this.jumpType = jumpType;
        this.url = url;
        this.isHot = isHot;
        this.isDefault = isDefault;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getJumpType() {
        return jumpType;
    }

    public void setJumpType(Boolean jumpType) {
        this.jumpType = jumpType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
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
        ShopKeyword that = (ShopKeyword) o;
        return Objects.equals(id, that.id) && Objects.equals(keyword, that.keyword) && Objects.equals(jumpType, that.jumpType) && Objects.equals(url, that.url) && Objects.equals(isHot, that.isHot) && Objects.equals(isDefault, that.isDefault) && Objects.equals(sort, that.sort) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyword, jumpType, url, isHot, isDefault, sort, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopKeyword{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", jumpType=" + jumpType +
                ", url='" + url + '\'' +
                ", isHot=" + isHot +
                ", isDefault=" + isDefault +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}