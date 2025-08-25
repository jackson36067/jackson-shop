package com.jackson.dto;

import java.util.Objects;

public class MemberRegisterDTO {
    private String username;
    private String email;
    private String emailCode;
    private String password;
    private String phone;

    public MemberRegisterDTO() {
    }

    public MemberRegisterDTO(String username, String email, String emailCode, String password, String phone) {
        this.username = username;
        this.email = email;
        this.emailCode = emailCode;
        this.password = password;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MemberRegisterDTO that = (MemberRegisterDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(emailCode, that.emailCode) && Objects.equals(password, that.password) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, emailCode, password, phone);
    }

    @Override
    public String toString() {
        return "MemberRegisterDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", emailCode='" + emailCode + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

