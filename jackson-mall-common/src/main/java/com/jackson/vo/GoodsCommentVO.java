package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class GoodsCommentVO implements Serializable {
    private Long id;
    private String content;
    private String adminContent; // 管理员回复消息
    private LocalDateTime createTime;
    private String nickname;
    private String avatar;
    private Short star; // 该评论给的星数
    private Boolean hasPicture;
    private List<String> picUrls;

    public GoodsCommentVO() {
    }

    public GoodsCommentVO(Long id, String content, String adminContent, LocalDateTime createTime, String nickname, String avatar, Short star, Boolean hasPicture, List<String> picUrl) {
        this.id = id;
        this.content = content;
        this.adminContent = adminContent;
        this.createTime = createTime;
        this.nickname = nickname;
        this.avatar = avatar;
        this.star = star;
        this.hasPicture = hasPicture;
        this.picUrls = picUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Short getStar() {
        return star;
    }

    public void setStar(Short star) {
        this.star = star;
    }

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodsCommentVO that = (GoodsCommentVO) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(adminContent, that.adminContent) && Objects.equals(createTime, that.createTime) && Objects.equals(nickname, that.nickname) && Objects.equals(avatar, that.avatar) && Objects.equals(star, that.star) && Objects.equals(hasPicture, that.hasPicture) && Objects.equals(picUrls, that.picUrls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, adminContent, createTime, nickname, avatar, star, hasPicture, picUrls);
    }

    @Override
    public String toString() {
        return "GoodsCommentVO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", adminContent='" + adminContent + '\'' +
                ", createTime=" + createTime +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", star=" + star +
                ", hasPicture=" + hasPicture +
                ", picUrl='" + picUrls + '\'' +
                '}';
    }
}
