package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class StoreVO implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private Boolean isFollow;
    private Integer fansNumber;

    public StoreVO() {
    }

    public StoreVO(Long id, String name, String avatar, Boolean isFollow, Integer fansNumber) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.isFollow = isFollow;
        this.fansNumber = fansNumber;
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

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StoreVO storeVO = (StoreVO) o;
        return Objects.equals(id, storeVO.id) && Objects.equals(name, storeVO.name) && Objects.equals(avatar, storeVO.avatar) && Objects.equals(isFollow, storeVO.isFollow) && Objects.equals(fansNumber, storeVO.fansNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatar, isFollow, fansNumber);
    }

    @Override
    public String toString() {
        return "StoreVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isFollow=" + isFollow +
                ", fansNumber=" + fansNumber +
                '}';
    }
}
