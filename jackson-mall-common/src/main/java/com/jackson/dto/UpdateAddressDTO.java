package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UpdateAddressDTO implements Serializable {
    private Long addressId;
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String areaCode;
    private String postalCode;
    private String tel;
    private String tag;
    private Short isDefault;
    private String name;

    public UpdateAddressDTO() {
    }

    public UpdateAddressDTO(Long addressId, String province, String city, String county, String addressDetail, String areaCode, String postalCode, String tel, String tag, Short isDefault, String name) {
        this.addressId = addressId;
        this.province = province;
        this.city = city;
        this.county = county;
        this.addressDetail = addressDetail;
        this.areaCode = areaCode;
        this.postalCode = postalCode;
        this.tel = tel;
        this.tag = tag;
        this.isDefault = isDefault;
        this.name = name;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Short getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Short isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateAddressDTO that = (UpdateAddressDTO) o;
        return Objects.equals(addressId, that.addressId) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(county, that.county) && Objects.equals(addressDetail, that.addressDetail) && Objects.equals(areaCode, that.areaCode) && Objects.equals(postalCode, that.postalCode) && Objects.equals(tel, that.tel) && Objects.equals(tag, that.tag) && Objects.equals(isDefault, that.isDefault) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, province, city, county, addressDetail, areaCode, postalCode, tel, tag, isDefault, name);
    }

    @Override
    public String toString() {
        return "UpdateAddressDTO{" +
                "addressId=" + addressId +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", AddressDetail='" + addressDetail + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                ", name='" + name + '\'' +
                '}';
    }
}