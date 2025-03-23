package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class FollowStoreVO implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private String followTime;
    private Long storeId;

    public FollowStoreVO() {
    }

    public FollowStoreVO(String name, Long id, String avatar, String followTime, Long storeId) {
        this.name = name;
        this.id = id;
        this.avatar = avatar;
        this.followTime = followTime;
        this.storeId = storeId;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FollowStoreVO that = (FollowStoreVO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(avatar, that.avatar) && Objects.equals(followTime, that.followTime) && Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatar, followTime, storeId);
    }

    @Override
    public String toString() {
        return "FollowStoreVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", followTime='" + followTime + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
