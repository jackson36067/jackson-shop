package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class AddAddressDTO implements Serializable {
    private String name;
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String areaCode;
    private String postalCode;
    private String tel;
    private String tag;
    private Short isDefault;

    public AddAddressDTO() {
    }

    public AddAddressDTO(String name, String province, String city, String country, String addressDetail, String areaCode, String postalCode, String tel, String tag, Short isDefault) {
        this.name = name;
        this.province = province;
        this.city = city;
        this.county = country;
        this.addressDetail = addressDetail;
        this.areaCode = areaCode;
        this.postalCode = postalCode;
        this.tel = tel;
        this.tag = tag;
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Short getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Short isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddAddressDTO that = (AddAddressDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(county, that.county) && Objects.equals(addressDetail, that.addressDetail) && Objects.equals(areaCode, that.areaCode) && Objects.equals(postalCode, that.postalCode) && Objects.equals(tel, that.tel) && Objects.equals(tag, that.tag) && Objects.equals(isDefault, that.isDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, province, city, county, addressDetail, areaCode, postalCode, tel, tag, isDefault);
    }

    @Override
    public String toString() {
        return "AddAddressDTO{" +
                "name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + county + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}