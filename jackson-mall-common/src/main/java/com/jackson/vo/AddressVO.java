package com.jackson.vo;

import java.io.Serializable;
import java.util.Objects;

public class AddressVO implements Serializable {
    private Long id;
    private String name;
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private String tel;
    private String tag;
    private Short isDefault;

    public AddressVO() {
    }

    public AddressVO(Long id, String name, String province, String city, String county, String addressDetail, String tel, String tag, Short isDefault) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.city = city;
        this.county = county;
        this.addressDetail = addressDetail;
        this.tel = tel;
        this.tag = tag;
        this.isDefault = isDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddressVO addressVO = (AddressVO) o;
        return Objects.equals(id, addressVO.id) && Objects.equals(name, addressVO.name) && Objects.equals(province, addressVO.province) && Objects.equals(city, addressVO.city) && Objects.equals(county, addressVO.county) && Objects.equals(addressDetail, addressVO.addressDetail) && Objects.equals(tel, addressVO.tel) && Objects.equals(tag, addressVO.tag) && Objects.equals(isDefault, addressVO.isDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, province, city, county, addressDetail, tel, tag, isDefault);
    }

    @Override
    public String toString() {
        return "AddressVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}