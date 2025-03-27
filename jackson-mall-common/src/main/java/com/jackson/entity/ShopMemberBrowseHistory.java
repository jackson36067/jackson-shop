package com.jackson.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "member_browse_history")
@EntityListeners(AuditingEntityListener.class)
public class ShopMemberBrowseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "type", nullable = false)
    private Short type;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "browse_time", nullable = false)
    @CreatedDate
    private LocalDateTime browseTime;

    public ShopMemberBrowseHistory() {
    }

    public ShopMemberBrowseHistory(Long id, Long memberId, Short type, Long goodsId, Long storeId, Long commentId, LocalDateTime browseTime) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.goodsId = goodsId;
        this.storeId = storeId;
        this.commentId = commentId;
        this.browseTime = browseTime;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(LocalDateTime browseTime) {
        this.browseTime = browseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopMemberBrowseHistory that = (ShopMemberBrowseHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(memberId, that.memberId) && Objects.equals(type, that.type) && Objects.equals(goodsId, that.goodsId) && Objects.equals(storeId, that.storeId) && Objects.equals(commentId, that.commentId) && Objects.equals(browseTime, that.browseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, type, goodsId, storeId, commentId, browseTime);
    }

    @Override
    public String toString() {
        return "ShopMemberBrowseHistory{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", type=" + type +
                ", goodsId=" + goodsId +
                ", storeId=" + storeId +
                ", commentId=" + commentId +
                ", browseTime=" + browseTime +
                '}';
    }
}