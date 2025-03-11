package com.jackson.dto;

import java.util.Objects;

/**
 * 支持两种登录形式, 手机号登录就使用密码, 邮箱登录使用验证码
 */
public class MemberLoginDTO {
    private String email;
    private String nickname;
    private String password;
    private String emailCode;

    public MemberLoginDTO() {
    }

    public MemberLoginDTO(String email, String nickname, String password, String emailCode) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.emailCode = emailCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String mobile) {
        this.nickname = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberLoginDTO that = (MemberLoginDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname) && Objects.equals(password, that.password) && Objects.equals(emailCode, that.emailCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, nickname, password, emailCode);
    }

    @Override
    public String toString() {
        return "MemberLoginDTO{" +
                "email='" + email + '\'' +
                ", mobile='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", emailCode='" + emailCode + '\'' +
                '}';
    }
}
