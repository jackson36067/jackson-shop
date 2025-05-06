package com.jackson.vo;

import java.util.Objects;

public class ChatMessageVO {
    private Long id;
    private Long userId; // 消息用户id
    private String avatar;
    private String name;
    private String message;
    private Boolean isRead; // 是否已读
    private Long receiverId; // 接收者id

    public ChatMessageVO() {
    }

    public ChatMessageVO(String avatar, Long id, Long userId, String name, String message, Boolean isRead, Long receiverId) {
        this.avatar = avatar;
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.message = message;
        this.isRead = isRead;
        this.receiverId = receiverId;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessageVO that = (ChatMessageVO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(avatar, that.avatar) && Objects.equals(name, that.name) && Objects.equals(message, that.message) && Objects.equals(isRead, that.isRead) && Objects.equals(receiverId, that.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, avatar, name, message, isRead, receiverId);
    }

    @Override
    public String toString() {
        return "ChatMessageVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                ", receiverId=" + receiverId +
                '}';
    }
}