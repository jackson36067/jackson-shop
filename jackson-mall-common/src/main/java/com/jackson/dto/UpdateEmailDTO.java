package com.jackson.dto;

import java.util.Objects;

public class UpdateEmailDTO {
    private String code;
    private String newEmail;

    public UpdateEmailDTO() {
    }

    public UpdateEmailDTO(String code, String newEmail) {
        this.code = code;
        this.newEmail = newEmail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEmailDTO that = (UpdateEmailDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(newEmail, that.newEmail);
    }

    @Override
    public String toString() {
        return "UpdateEmailDTO{" +
                "code='" + code + '\'' +
                ", newEmail='" + newEmail + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, newEmail);
    }
}