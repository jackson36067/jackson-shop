package com.jackson.service;

import com.jackson.dto.ChatMessageDTO;
import com.jackson.result.Result;
import com.jackson.vo.ChatThreadDetailVO;
import com.jackson.vo.ChatThreadMessageVO;

import java.util.List;

public interface ChatMessageService {
    Result<List<ChatThreadMessageVO>> getChatThreadList();

    Result<Long> getUnReadMessageCount();

    Result<ChatThreadDetailVO> getChatMessageList(Long id);

    void sendChatMessage(ChatMessageDTO chatMessageDTO);
}
