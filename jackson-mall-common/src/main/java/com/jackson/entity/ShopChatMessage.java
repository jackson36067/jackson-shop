package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shop_chat_messages")
@EntityListeners(AuditingEntityListener.class)
public class ShopChatMessage {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "message", nullable = false)
    private String message;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "sent_time")
    @CreatedDate
    private LocalDateTime sentTime;

    @Column(name = "store_id")
    private Long storeId;

    @ColumnDefault("0")
    @Column(name = "is_read")
    private Boolean isRead;

    @ColumnDefault("0")
    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "chat_thread_id", referencedColumnName = "id")
    private ShopChatThread shopChatThread;

    public ShopChatMessage() {
    }

    public ShopChatMessage(Long id, Long senderId, Long receiverId, String message, LocalDateTime sentTime, Long storeId, Boolean isRead, Boolean deleted, ShopChatThread shopChatThread) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.sentTime = sentTime;
        this.storeId = storeId;
        this.isRead = isRead;
        this.deleted = deleted;
        this.shopChatThread = shopChatThread;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ShopChatThread getShopChatThread() {
        return shopChatThread;
    }

    public void setShopChatThread(ShopChatThread shopChatThread) {
        this.shopChatThread = shopChatThread;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopChatMessage that = (ShopChatMessage) o;
        return Objects.equals(id, that.id) && Objects.equals(senderId, that.senderId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(message, that.message) && Objects.equals(sentTime, that.sentTime) && Objects.equals(storeId, that.storeId) && Objects.equals(isRead, that.isRead) && Objects.equals(deleted, that.deleted) && Objects.equals(shopChatThread, that.shopChatThread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, receiverId, message, sentTime, storeId, isRead, deleted, shopChatThread);
    }

    @Override
    public String toString() {
        return "ShopChatMessage{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", message='" + message + '\'' +
                ", sentTime=" + sentTime +
                ", storeId=" + storeId +
                ", isRead=" + isRead +
                ", deleted=" + deleted +
                ", shopChatThread=" + shopChatThread +
                '}';
    }
}