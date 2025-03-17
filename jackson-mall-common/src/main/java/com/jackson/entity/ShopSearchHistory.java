package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_search_history")
public class ShopSearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "keyword", nullable = false, length = 63)
    private String keyword;

    @ColumnDefault("''")
    @Column(name = "`from`", nullable = false, length = 63)
    private String from;

    @Column(name = "has_goods")
    private Boolean hasGoods;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopSearchHistory() {
    }

    public ShopSearchHistory(Long id, Long userId, String keyword, String from, Boolean hasGoods, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
        this.id = id;
        this.userId = userId;
        this.keyword = keyword;
        this.from = from;
        this.hasGoods = hasGoods;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Boolean getHasGoods() {
        return hasGoods;
    }

    public void setHasGoods(Boolean hasGoods) {
        this.hasGoods = hasGoods;
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
        ShopSearchHistory that = (ShopSearchHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(keyword, that.keyword) && Objects.equals(from, that.from) && Objects.equals(hasGoods, that.hasGoods) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, keyword, from, hasGoods, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopSearchHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", keyword='" + keyword + '\'' +
                ", from='" + from + '\'' +
                ", hasGoods=" + hasGoods +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}