package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "shop_member_follow_store")
@EntityListeners(AuditingEntityListener.class)
public class ShopMemberFollowStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @ColumnDefault("(now())")
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private LocalDateTime createTime;

    public ShopMemberFollowStore() {
    }

    public ShopMemberFollowStore(Long id, Long memberId, Long storeId, LocalDateTime createTime) {
        this.id = id;
        this.memberId = memberId;
        this.storeId = storeId;
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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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
        ShopMemberFollowStore that = (ShopMemberFollowStore) o;
        return Objects.equals(id, that.id) && Objects.equals(memberId, that.memberId) && Objects.equals(storeId, that.storeId) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, storeId, createTime);
    }

    @Override
    public String toString() {
        return "ShopMemberFollowStore{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", storeId=" + storeId +
                ", createTime=" + createTime +
                '}';
    }
}