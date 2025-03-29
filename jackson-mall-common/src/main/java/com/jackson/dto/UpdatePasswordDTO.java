package com.jackson.dto;

import java.util.Objects;

public class UpdatePasswordDTO {
    private String password;
    private String newPassword;
    public UpdatePasswordDTO() {
    }
    public UpdatePasswordDTO(  String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdatePasswordDTO that = (UpdatePasswordDTO) o;
        return  Objects.equals(password, that.password) && Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash( password, newPassword);
    }

    @Override
    public String toString() {
        return "UpdatePasswordDTO{" +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}