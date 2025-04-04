package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_comment")
@EntityListeners(AuditingEntityListener.class)
public class ShopComment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("0")
    @Column(name = "value_id", nullable = false)
    private Long valueId;

    @ColumnDefault("0")
    @Column(name = "type", nullable = false)
    private Short type;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @ColumnDefault("''")
    @Column(name = "content", length = 1023)
    private String content;

    @ColumnDefault("''")
    @Column(name = "admin_content", length = 511)
    private String adminContent;

    @ColumnDefault("0")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ColumnDefault("0")
    @Column(name = "has_picture")
    private Boolean hasPicture;

    @Column(name = "pic_urls", length = 1023)
    private String picUrls;

    @ColumnDefault("1")
    @Column(name = "star")
    private Short star;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;

    public ShopComment() {
    }

    public ShopComment(Long id, Long valueId, Short type, String avatar, String content, String adminContent, Long userId, Boolean hasPicture, String picUrls, Short star, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
        this.id = id;
        this.valueId = valueId;
        this.type = type;
        this.avatar = avatar;
        this.content = content;
        this.adminContent = adminContent;
        this.userId = userId;
        this.hasPicture = hasPicture;
        this.picUrls = picUrls;
        this.star = star;
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

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdminContent() {
        return adminContent;
    }

    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public Short getStar() {
        return star;
    }

    public void setStar(Short star) {
        this.star = star;
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
        if (o == null || getClass() != o.getClass()) return false;
        ShopComment that = (ShopComment) o;
        return Objects.equals(id, that.id) && Objects.equals(valueId, that.valueId) && Objects.equals(type, that.type) && Objects.equals(avatar, that.avatar) && Objects.equals(content, that.content) && Objects.equals(adminContent, that.adminContent) && Objects.equals(userId, that.userId) && Objects.equals(hasPicture, that.hasPicture) && Objects.equals(picUrls, that.picUrls) && Objects.equals(star, that.star) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valueId, type, avatar, content, adminContent, userId, hasPicture, picUrls, star, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopComment{" +
                "id=" + id +
                ", valueId=" + valueId +
                ", type=" + type +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", adminContent='" + adminContent + '\'' +
                ", userId=" + userId +
                ", hasPicture=" + hasPicture +
                ", picUrls='" + picUrls + '\'' +
                ", star=" + star +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}