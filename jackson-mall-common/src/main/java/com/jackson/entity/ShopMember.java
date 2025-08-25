package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shop_member")
@EntityListeners(AuditingEntityListener.class)
public class ShopMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String password;
    private Short gender;
    private LocalDate birthday;
    private String email;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private Short userLevel;
    private String nickname;
    private String mobile;
    private String avatar;
    private Short status;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
    private Boolean delFlag;



    public ShopMember() {
    }

    public ShopMember(Long id, String password, Short gender, LocalDate birthday, String email, LocalDateTime lastLoginTime, String lastLoginIp, Short userLevel, String nickname, String mobile, String avatar, Short status, LocalDateTime createTime, LocalDateTime updateTime, Boolean delFlag) {
        this.id = id;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginIp = lastLoginIp;
        this.userLevel = userLevel;
        this.nickname = nickname;
        this.mobile = mobile;
        this.avatar = avatar;
        this.status = status;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Short getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Short userLevel) {
        this.userLevel = userLevel;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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
        ShopMember that = (ShopMember) o;
        return Objects.equals(id, that.id) && Objects.equals(password, that.password) && Objects.equals(gender, that.gender) && Objects.equals(birthday, that.birthday) && Objects.equals(email, that.email) && Objects.equals(lastLoginTime, that.lastLoginTime) && Objects.equals(lastLoginIp, that.lastLoginIp) && Objects.equals(userLevel, that.userLevel) && Objects.equals(nickname, that.nickname) && Objects.equals(mobile, that.mobile) && Objects.equals(avatar, that.avatar) && Objects.equals(status, that.status) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, gender, birthday, email, lastLoginTime, lastLoginIp, userLevel, nickname, mobile, avatar, status, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopMember{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", userLevel=" + userLevel +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}