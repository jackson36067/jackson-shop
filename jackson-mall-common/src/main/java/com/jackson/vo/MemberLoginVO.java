package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MemberLoginVO implements Serializable {
    private Long id;
    private String nickname;
    private String avatar;
    private String token;
    private Short gender;
    private LocalDate birthday;
    private String email;
    private String mobile;

    public MemberLoginVO() {
    }

    public MemberLoginVO(Long id, String nickname, String avatar, String token, Short gender, LocalDate birthday, String email, String mobile) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.token = token;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberLoginVO that = (MemberLoginVO) o;
        return Objects.equals(id, that.id) && Objects.equals(nickname, that.nickname) && Objects.equals(avatar, that.avatar) && Objects.equals(token, that.token) && Objects.equals(gender, that.gender) && Objects.equals(birthday, that.birthday) && Objects.equals(email, that.email) && Objects.equals(mobile, that.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, avatar, token, gender, birthday, email, mobile);
    }

    @Override
    public String toString() {
        return "MemberLoginVO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", token='" + token + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
