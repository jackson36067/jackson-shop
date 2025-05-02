package com.jackson.controller;

import com.jackson.result.Result;
import com.jackson.service.ChatMessageService;
import com.jackson.vo.ChatThreadDetailVO;
import com.jackson.vo.ChatThreadMessageVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class ChatMessageController {

    @Resource
    private ChatMessageService chatMessageService;

    /**
     * 获取用户聊天列表
     *
     * @return
     */
    @GetMapping("/thread/list")
    public Result<List<ChatThreadMessageVO>> getChatThreadList() {
        return chatMessageService.getChatThreadList();
    }

    /**
     * 获取用户未读消息数量
     *
     * @return 返回未读消息个数
     */
    @GetMapping("/unRead")
    public Result<Long> getUnReadMessageCount() {
        return chatMessageService.getUnReadMessageCount();
    }

    /**
     * 获取消息队列详情信息
     *
     * @param id 消息队列id
     * @return 消息队列详情信息
     */
    @GetMapping("/{id}")
    public Result<ChatThreadDetailVO> getChatMessageList(@PathVariable Long id) {
        return chatMessageService.getChatMessageList(id);
    }
}
