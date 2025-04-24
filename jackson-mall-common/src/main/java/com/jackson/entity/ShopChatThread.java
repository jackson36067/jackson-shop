package com.jackson.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shop_chat_threads")
@EntityListeners(AuditingEntityListener.class)
public class ShopChatThread {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "store_id")
    private Long storeId;

    @Lob
    @Column(name = "last_message")
    private String lastMessage;

    @ColumnDefault("(now())")
    @Column(name = "last_message_time")
    private LocalDateTime lastMessageTime;

    @ColumnDefault("1")
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_time")
    @CreatedDate
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    @LastModifiedDate
    private LocalDateTime updatedTime;

    @OneToMany(mappedBy = "shopChatThread", cascade = CascadeType.ALL)
    private List<ShopChatMessage> shopChatMessages;

    public ShopChatThread() {
    }

    public ShopChatThread(Long id, Long userId, Long receiverId, Long storeId, String lastMessage, LocalDateTime lastMessageTime, Boolean isActive, LocalDateTime createdTime, LocalDateTime updatedTime, List<ShopChatMessage> shopChatMessages) {
        this.id = id;
        this.userId = userId;
        this.receiverId = receiverId;
        this.storeId = storeId;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.shopChatMessages = shopChatMessages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public LocalDateTime getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(LocalDateTime lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<ShopChatMessage> getShopChatMessages() {
        return shopChatMessages;
    }

    public void setShopChatMessages(List<ShopChatMessage> shopChatMessages) {
        this.shopChatMessages = shopChatMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopChatThread that = (ShopChatThread) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(storeId, that.storeId) && Objects.equals(lastMessage, that.lastMessage) && Objects.equals(lastMessageTime, that.lastMessageTime) && Objects.equals(isActive, that.isActive) && Objects.equals(createdTime, that.createdTime) && Objects.equals(updatedTime, that.updatedTime) && Objects.equals(shopChatMessages, that.shopChatMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, receiverId, storeId, lastMessage, lastMessageTime, isActive, createdTime, updatedTime, shopChatMessages);
    }

    @Override
    public String toString() {
        return "ShopChatThread{" +
                "id=" + id +
                ", userId=" + userId +
                ", receiverId=" + receiverId +
                ", storeId=" + storeId +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastMessageTime=" + lastMessageTime +
                ", isActive=" + isActive +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", shopChatMessages=" + shopChatMessages +
                '}';
    }
}