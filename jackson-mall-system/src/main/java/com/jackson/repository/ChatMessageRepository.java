package com.jackson.repository;

import com.jackson.entity.ShopChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ShopChatMessage, Long> {
}
