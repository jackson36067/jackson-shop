package com.jackson.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MemberVO implements Serializable {
    private Long id;
    private String nickname;
    private String avatar;
    private Short gender;
    private LocalDate birthday;

    public MemberVO() {
    }

    public MemberVO(Long id, String nickname, String avatar, Short gender, LocalDate birthday) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.gender = gender;
        this.birthday = birthday;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberVO memberVO = (MemberVO) o;
        return Objects.equals(id, memberVO.id) && Objects.equals(nickname, memberVO.nickname) && Objects.equals(avatar, memberVO.avatar) && Objects.equals(gender, memberVO.gender) && Objects.equals(birthday, memberVO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, avatar, gender, birthday);
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
