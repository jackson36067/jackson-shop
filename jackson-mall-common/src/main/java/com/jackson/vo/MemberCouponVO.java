package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MemberCouponVO implements Serializable {
    private Long storeId; // 店铺id
    private String name; // 店铺名称
    private String avatar; //店铺头像
    private List<MemberCouponItemVO> memberCouponItemVOList;
    public MemberCouponVO() {
    }

    public MemberCouponVO(Long storeId, String name, String avatar, List<MemberCouponItemVO> memberCouponItemVOList) {
        this.storeId = storeId;
        this.name = name;
        this.avatar = avatar;
        this.memberCouponItemVOList = memberCouponItemVOList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public List<MemberCouponItemVO> getMemberCouponItemVOList() {
        return memberCouponItemVOList;
    }

    public void setMemberCouponItemVOList(List<MemberCouponItemVO> memberCouponItemVOList) {
        this.memberCouponItemVOList = memberCouponItemVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MemberCouponVO that = (MemberCouponVO) o;
        return Objects.equals(storeId, that.storeId) && Objects.equals(name, that.name) && Objects.equals(avatar, that.avatar) && Objects.equals(memberCouponItemVOList, that.memberCouponItemVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, name, avatar, memberCouponItemVOList);
    }

    @Override
    public String toString() {
        return "MemberCouponVO{" +
                "storeId=" + storeId +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", memberCouponItemVOList=" + memberCouponItemVOList +
                '}';
    }
}
