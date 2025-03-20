package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_member_collect_goods")
@EntityListeners(AuditingEntityListener.class)
public class ShopMemberCollectGood implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "goods_id", nullable = false)
    private Long goodsId;

    @ColumnDefault("(now())")
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private LocalDateTime createTime;

    public ShopMemberCollectGood() {
    }

    public ShopMemberCollectGood(Long id, Long memberId, Long goodsId, LocalDateTime createTime) {
        this.id = id;
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopMemberCollectGood that = (ShopMemberCollectGood) o;
        return Objects.equals(id, that.id) && Objects.equals(memberId, that.memberId) && Objects.equals(goodsId, that.goodsId) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, goodsId, createTime);
    }

    @Override
    public String toString() {
        return "ShopMemberCollectGood{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", goodsId=" + goodsId +
                ", createTime=" + createTime +
                '}';
    }
}