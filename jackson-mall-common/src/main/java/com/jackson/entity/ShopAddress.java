package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_address")
@EntityListeners(AuditingEntityListener.class)
public class ShopAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("''")
    @Column(name = "name", nullable = false, length = 63)
    private String name;

    @ColumnDefault("0")
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "province", nullable = false, length = 63)
    private String province;

    @Column(name = "city", nullable = false, length = 63)
    private String city;

    @Column(name = "county", nullable = false, length = 63)
    private String county;

    @ColumnDefault("''")
    @Column(name = "address_detail", nullable = false, length = 127)
    private String addressDetail;

    @Column(name = "area_code", length = 6)
    private String areaCode;

    @Column(name = "postal_code", length = 6)
    private String postalCode;

    @ColumnDefault("''")
    @Column(name = "tel", nullable = false, length = 20)
    private String tel;

    @Column(name = "tag")
    private String tag;

    @ColumnDefault("0")
    @Column(name = "is_default", nullable = false)
    private Short isDefault;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Short delFlag;

    public ShopAddress() {
    }

    public ShopAddress(Long id, String name, Long memberId, String province, String city, String county, String addressDetail, String areaCode, String postalCode, String tel, String tag, Short isDefault, LocalDateTime createTime, LocalDateTime updateTime, Short delFlag) {
        this.id = id;
        this.name = name;
        this.memberId = memberId;
        this.province = province;
        this.city = city;
        this.county = county;
        this.addressDetail = addressDetail;
        this.areaCode = areaCode;
        this.postalCode = postalCode;
        this.tel = tel;
        this.tag = tag;
        this.isDefault = isDefault;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopAddress that = (ShopAddress) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(memberId, that.memberId) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(county, that.county) && Objects.equals(addressDetail, that.addressDetail) && Objects.equals(areaCode, that.areaCode) && Objects.equals(postalCode, that.postalCode) && Objects.equals(tel, that.tel) && Objects.equals(tag, that.tag) && Objects.equals(isDefault, that.isDefault) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, memberId, province, city, county, addressDetail, areaCode, postalCode, tel, tag, isDefault, createTime, updateTime, delFlag);
    }

    @Override
    public String toString() {
        return "ShopAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memberId=" + memberId +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}
