package com.jackson.repository;

import com.jackson.entity.ShopChatThread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatThreadRepository extends JpaRepository<ShopChatThread, Long> {
    // 通过发送者,接收者以及店铺id获取聊天列表
    ShopChatThread findByUserIdAndReceiverIdAndStoreId(Long userId, Long receiverId, Long storeId);
}
