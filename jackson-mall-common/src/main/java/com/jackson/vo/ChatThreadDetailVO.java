package com.jackson.vo;

import java.util.List;
import java.util.Objects;

public class ChatThreadDetailVO {
    private Long id; // 消息聊天队列id
    private String name; // 店铺名称或者用户名称
    private Long receiverId; // 接收者id, 接收者为用户时存在
    private Long storeId; // 存在,接收者为店铺
    private List<ChatMessageVO> chatMessageList; // 双方消息列表

    public ChatThreadDetailVO() {
    }

    public ChatThreadDetailVO(Long id, String name, Long receiverId, Long storeId, List<ChatMessageVO> chatMessageList) {
        this.id = id;
        this.name = name;
        this.receiverId = receiverId;
        this.storeId = storeId;
        this.chatMessageList = chatMessageList;
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

    public List<ChatMessageVO> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessageVO> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChatThreadDetailVO that = (ChatThreadDetailVO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(receiverId, that.receiverId) && Objects.equals(storeId, that.storeId) && Objects.equals(chatMessageList, that.chatMessageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, receiverId, storeId, chatMessageList);
    }

    @Override
    public String toString() {
        return "ChatThreadDetailVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", receiverId=" + receiverId +
                ", storeId=" + storeId +
                ", chatMessageList=" + chatMessageList +
                '}';
    }
}