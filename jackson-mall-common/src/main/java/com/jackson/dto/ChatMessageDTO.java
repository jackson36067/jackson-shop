package com.jackson.dto;

import java.util.Objects;

public class ChatMessageDTO {
    private Long id; // chatTreadId
    private Long userId;
    private Long receiverId;
    private String message;
    private Long receiverStoreId; // 存在,接收者为一个店铺客服
    private Long senderStoreId; // 存在,发送者为一个店铺客服
    private String name;
    private String avatar;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Long id, Long userId, Long receiverId, String message, Long senderStoreId, Long receiverStoreId, String name, String avatar) {
        this.id = id;
        this.userId = userId;
        this.receiverId = receiverId;
        this.message = message;
        this.receiverStoreId = receiverStoreId;
        this.senderStoreId = senderStoreId;
        this.name = name;
        this.avatar = avatar;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderStoreId() {
        return senderStoreId;
    }

    public void setSenderStoreId(Long senderStoreId) {
        this.senderStoreId = senderStoreId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getReceiverStoreId() {
        return receiverStoreId;
    }

    public void setReceiverStoreId(Long receiverStoreId) {
        this.receiverStoreId = receiverStoreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessageDTO that = (ChatMessageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(message, that.message) && Objects.equals(receiverStoreId, that.receiverStoreId) && Objects.equals(senderStoreId, that.senderStoreId) && Objects.equals(name, that.name) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, receiverId, message, receiverStoreId, senderStoreId, name, avatar);
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", receiverId=" + receiverId +
                ", message='" + message + '\'' +
                ", receiverStoreId=" + receiverStoreId +
                ", senderStoreId=" + senderStoreId +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}