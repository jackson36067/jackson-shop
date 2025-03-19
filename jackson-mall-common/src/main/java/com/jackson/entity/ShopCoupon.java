package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shop_coupon")
public class ShopCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ColumnDefault("0")
    @Column(name = "num", nullable = false)
    private Integer num;

    @ColumnDefault("0")
    @Column(name = "receive_num", nullable = false)
    private Integer receiveNum;

    @ColumnDefault("0")
    @Column(name = "discount", nullable = false)
    private Integer discount;

    @ColumnDefault("0")
    @Column(name = "min", nullable = false)
    private Integer min;

    @ColumnDefault("0")
    @Column(name = "status", nullable = false)
    private Short status;

    @ColumnDefault("1")
    @Column(name = "type", nullable = false)
    private Short type;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "create_by", nullable = false)
    private String createBy;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "update_by")
    private String updateBy;

    @ColumnDefault("0")
    @Column(name = "del_flag", nullable = false)
    private Short delFlag;

    @ManyToOne
    @JoinColumn(name = "goods_id",referencedColumnName = "id")
    private ShopGood shopGood;

    @ManyToMany
    @JoinTable(
            name = "shop_member_coupon",
            joinColumns = {@JoinColumn(name = "coupon_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}
    )
    private List<ShopMember> shopMembers;
    public ShopCoupon() {
    }

    public ShopCoupon(Long id, String title, Integer num, Integer receiveNum, Integer discount, Integer min, Short status, Short type, LocalDateTime expireTime, LocalDateTime createTime, String createBy, LocalDateTime updateTime, String updateBy, Short delFlag, ShopGood shopGood, List<ShopMember> shopMembers) {
        this.id = id;
        this.title = title;
        this.num = num;
        this.receiveNum = receiveNum;
        this.discount = discount;
        this.min = min;
        this.status = status;
        this.type = type;
        this.expireTime = expireTime;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
        this.delFlag = delFlag;
        this.shopGood = shopGood;
        this.shopMembers = shopMembers;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Integer receiveNum) {
        this.receiveNum = receiveNum;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    public ShopGood getShopGood() {
        return shopGood;
    }

    public void setShopGood(ShopGood shopGood) {
        this.shopGood = shopGood;
    }

    public List<ShopMember> getShopMembers() {
        return shopMembers;
    }

    public void setShopMembers(List<ShopMember> shopMembers) {
        this.shopMembers = shopMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCoupon that = (ShopCoupon) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(num, that.num) && Objects.equals(receiveNum, that.receiveNum) && Objects.equals(discount, that.discount) && Objects.equals(min, that.min) && Objects.equals(status, that.status) && Objects.equals(type, that.type) && Objects.equals(expireTime, that.expireTime) && Objects.equals(createTime, that.createTime) && Objects.equals(createBy, that.createBy) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateBy, that.updateBy) && Objects.equals(delFlag, that.delFlag) && Objects.equals(shopGood, that.shopGood) && Objects.equals(shopMembers, that.shopMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, num, receiveNum, discount, min, status, type, expireTime, createTime, createBy, updateTime, updateBy, delFlag, shopGood, shopMembers);
    }

    @Override
    public String toString() {
        return "ShopCoupon{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", num=" + num +
                ", receiveNum=" + receiveNum +
                ", discount=" + discount +
                ", min=" + min +
                ", status=" + status +
                ", type=" + type +
                ", expireTime=" + expireTime +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", delFlag=" + delFlag +
                ", shopGood=" + shopGood +
                ", shopMembers=" + shopMembers +
                '}';
    }
}