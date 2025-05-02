package com.jackson.vo;

import java.util.Objects;

public class ChatThreadMessageVO {
    private Long id; // 聊天列表id
    private Long userId; // 发送者id, 有店铺id时是客服id
    private Long receiverId; // 接收者id
    private Long storeId; // 店铺id
    private String lastMessage; // 最后一条消息内容
    private String lastMessageTime; // 最后一个消息的时间, 转换成string类型
    private String name; // 没有店铺id时就是接收者名称, 有则是店铺名称
    private String avatar; // 没有店铺id是就是接收者头像, 有则是店铺头像
    private Long unReadCount;

    public ChatThreadMessageVO() {
    }

    public ChatThreadMessageVO(Long id, Long userId, Long receiverId, Long storeId, String lastMessage, String lastMessageTime, String name, String avatar, Long unReadCount) {
        this.id = id;
        this.userId = userId;
        this.receiverId = receiverId;
        this.storeId = storeId;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.name = name;
        this.avatar = avatar;
        this.unReadCount = unReadCount;
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

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
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

    public Long getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Long unReadCount) {
        this.unReadCount = unReadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChatThreadMessageVO that = (ChatThreadMessageVO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(storeId, that.storeId) && Objects.equals(lastMessage, that.lastMessage) && Objects.equals(lastMessageTime, that.lastMessageTime) && Objects.equals(name, that.name) && Objects.equals(avatar, that.avatar) && Objects.equals(unReadCount, that.unReadCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, receiverId, storeId, lastMessage, lastMessageTime, name, avatar, unReadCount);
    }

    @Override
    public String toString() {
        return "ChatThreadMessageVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", receiverId=" + receiverId +
                ", storeId=" + storeId +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastMessageTime='" + lastMessageTime + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", unReadCount=" + unReadCount +
                '}';
    }
}