package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CategoryVO implements Serializable {
    private Long id;
    private String name;
    private String remark;
    private String pid;
    private String iconUrl;
    private String picUrl;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<CategoryVO> subCategoryList;

    public CategoryVO() {
    }

    public CategoryVO(Long id, String name, String remark, String pid, String iconUrl, String picUrl, Integer sort, LocalDateTime createTime, LocalDateTime updateTime, List<CategoryVO> subCategoryList) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.pid = pid;
        this.iconUrl = iconUrl;
        this.picUrl = picUrl;
        this.sort = sort;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.subCategoryList = subCategoryList;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
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

    public List<CategoryVO> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<CategoryVO> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryVO that = (CategoryVO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(remark, that.remark) && Objects.equals(pid, that.pid) && Objects.equals(iconUrl, that.iconUrl) && Objects.equals(picUrl, that.picUrl) && Objects.equals(sort, that.sort) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(subCategoryList, that.subCategoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, remark, pid, iconUrl, picUrl, sort, createTime, updateTime, subCategoryList);
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", pid='" + pid + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", subCategoryList=" + subCategoryList +
                '}';
    }
}
