package com.jackson.repository;

import com.jackson.entity.ShopChatThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatThreadRepository extends JpaRepository<ShopChatThread, Long> {
    // 通过发送者,接收者获取聊天列表
    // 可以替换发送者以及接收者,防止保存与同一人的对话列表两次
    @Query("select s from ShopChatThread s where (s.userId = :userId and s.receiverId = :receiverId) or (s.userId = :receiverId and s.receiverId = :userId)")
    ShopChatThread findByUserIdAndReceiverId(@Param("userId") Long userId, @Param("receiverId") Long receiverId);

    List<ShopChatThread> findAllByReceiverIdAndIsDelete(Long receiverId, Boolean isDelete);

    List<ShopChatThread> findAllByUserIdAndIsDelete(Long userId, Boolean isDelete);
}
