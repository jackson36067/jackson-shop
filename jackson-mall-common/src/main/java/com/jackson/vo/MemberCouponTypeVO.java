package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MemberCouponTypeVO<T> implements Serializable {
    private String type;
    private List<T> memberCouponList;

    public MemberCouponTypeVO(String type, List<T> memberCouponList) {
        this.type = type;
        this.memberCouponList = memberCouponList;
    }

    public MemberCouponTypeVO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getMemberCouponList() {
        return memberCouponList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MemberCouponTypeVO<?> that = (MemberCouponTypeVO<?>) o;
        return Objects.equals(type, that.type) && Objects.equals(memberCouponList, that.memberCouponList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, memberCouponList);
    }

    @Override
    public String toString() {
        return "MemberCouponTypeVO{" +
                "type='" + type + '\'' +
                ", memberCouponList=" + memberCouponList +
                '}';
    }

    public void setMemberCouponList(List<T> memberCouponList) {
        this.memberCouponList = memberCouponList;
    }
}
