package com.jackson.dto;

import java.util.Objects;

public class MemberSendCodeDTO {
    private String email;

    public MemberSendCodeDTO() {
    }

    public MemberSendCodeDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberSendCodeDTO that = (MemberSendCodeDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "MemberSendCodeDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
