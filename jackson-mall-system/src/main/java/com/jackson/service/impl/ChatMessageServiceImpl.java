package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.MemberConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.ChatMessageDTO;
import com.jackson.entity.ShopChatMessage;
import com.jackson.entity.ShopChatThread;
import com.jackson.entity.ShopMember;
import com.jackson.entity.ShopStore;
import com.jackson.hanlder.MyWebSocketHandler;
import com.jackson.repository.ChatThreadRepository;
import com.jackson.repository.MemberRepository;
import com.jackson.repository.StoreRepository;
import com.jackson.result.Result;
import com.jackson.service.ChatMessageService;
import com.jackson.utils.DateTimeFormatUtils;
import com.jackson.vo.ChatMessageVO;
import com.jackson.vo.ChatThreadDetailVO;
import com.jackson.vo.ChatThreadMessageVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {
    @Resource
    private ChatThreadRepository chatThreadRepository;
    @Resource
    private StoreRepository storeRepository;
    @Resource
    private MemberRepository memberRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MyWebSocketHandler myWebSocketHandler;

    /**
     * 获取用户聊天列表
     *
     * @return
     */
    public Result<List<ChatThreadMessageVO>> getChatThreadList() {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            return Result.success(new ArrayList<>());
        }
        // 获取该用户的聊天列表,条件需要未被删除, 或者用户作为接收者的信息
        List<ShopChatThread> ShopChatThreadReceiverList = chatThreadRepository.findAllByReceiverIdAndIsDelete(userId, false);
        List<ChatThreadMessageVO> ChatThreadReciverList = ShopChatThreadReceiverList.stream()
                .map(shopChatThread -> {
                    ChatThreadMessageVO chatThreadMessageVO = BeanUtil.copyProperties(shopChatThread, ChatThreadMessageVO.class);
                    chatThreadMessageVO.setUserId(userId);
                    chatThreadMessageVO.setLastMessageTime(DateTimeFormatUtils.formatTime(shopChatThread.getLastMessageTime()));
                    // 发送者为店铺的客服,封装店铺信息
                    if (shopChatThread.getSenderStoreId() != null) {
                        ShopStore shopStore = storeRepository.findById(shopChatThread.getSenderStoreId()).get();
                        chatThreadMessageVO.setReceiverId(shopStore.getServiceId());
                        chatThreadMessageVO.setName(shopStore.getName());
                        chatThreadMessageVO.setAvatar(shopStore.getAvatar());
                        // 获取未读消息数量
                        Long unReadMessageCount = stringRedisTemplate.opsForZSet().size(String.format(MemberConstant.USER_MESSAGE_KEY, userId, shopStore.getServiceId()));
                        chatThreadMessageVO.setUnReadCount(unReadMessageCount);
                    } else {
                        // 发送者为普通用户,那么封装用户信息
                        ShopMember shopMember = memberRepository.findById(shopChatThread.getUserId()).get();
                        chatThreadMessageVO.setReceiverId(shopChatThread.getUserId());
                        chatThreadMessageVO.setName(shopMember.getNickname());
                        chatThreadMessageVO.setAvatar(shopMember.getAvatar());
                        // 获取未读消息数量
                        Long unReadMessageCount = stringRedisTemplate.opsForZSet().size(String.format(MemberConstant.USER_MESSAGE_KEY, userId, shopChatThread.getUserId()));
                        chatThreadMessageVO.setUnReadCount(unReadMessageCount);
                    }
                    return chatThreadMessageVO;
                })
                .toList();
        // 获取该用户的聊天列表,条件需要未被删除, 获取用户作为发送者者的信息
        List<ShopChatThread> ShopChatThreadSenderList = chatThreadRepository.findAllByUserIdAndIsDelete(userId, false);
        List<ChatThreadMessageVO> chatThreadSenderList = ShopChatThreadSenderList.stream()
                .map(shopChatThread -> {
                    ChatThreadMessageVO chatThreadMessageVO = BeanUtil.copyProperties(shopChatThread, ChatThreadMessageVO.class);
                    chatThreadMessageVO.setUserId(userId);
                    chatThreadMessageVO.setLastMessageTime(DateTimeFormatUtils.formatTime(shopChatThread.getLastMessageTime()));
                    // 发送者为店铺的客服,封装店铺信息
                    if (shopChatThread.getReceiverStoreId() != null) {
                        ShopStore shopStore = storeRepository.findById(shopChatThread.getReceiverStoreId()).get();
                        chatThreadMessageVO.setReceiverId(shopStore.getServiceId());
                        chatThreadMessageVO.setName(shopStore.getName());
                        chatThreadMessageVO.setAvatar(shopStore.getAvatar());
                        // 获取未读消息数量
                        Long unReadMessageCount = stringRedisTemplate.opsForZSet().size(String.format(MemberConstant.USER_MESSAGE_KEY, userId, shopStore.getServiceId()));
                        chatThreadMessageVO.setUnReadCount(unReadMessageCount);
                    } else {
                        // 发送者为普通用户,那么封装用户信息
                        ShopMember shopMember = memberRepository.findById(shopChatThread.getReceiverId()).get();
                        chatThreadMessageVO.setReceiverId(shopChatThread.getReceiverId());
                        chatThreadMessageVO.setName(shopMember.getNickname());
                        chatThreadMessageVO.setAvatar(shopMember.getAvatar());
                        // 获取未读消息数量
                        Long unReadMessageCount = stringRedisTemplate.opsForZSet().size(String.format(MemberConstant.USER_MESSAGE_KEY, userId, shopChatThread.getReceiverId()));
                        chatThreadMessageVO.setUnReadCount(unReadMessageCount);
                    }
                    return chatThreadMessageVO;
                })
                .toList();
        List<ChatThreadMessageVO> ChatThreadList = new ArrayList<>();
        ChatThreadList.addAll(chatThreadSenderList);
        ChatThreadList.addAll(ChatThreadReciverList);
        return Result.success(ChatThreadList);
    }

    /**
     * 获取用户未读消息数量
     *
     * @return 返回未读消息个数
     */
    public Result<Long> getUnReadMessageCount() {
        if (BaseContext.getCurrentId() == null) {
            return Result.success(0L);
        }
        // 构建匹配 pattern
        String pattern = String.format(MemberConstant.USER_MESSAGE_KEY_PREFIX, BaseContext.getCurrentId()) + "*";

        // 使用 SCAN 获取所有相关 key
        ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(1000).build();
        Cursor<byte[]> cursor = Objects.requireNonNull(stringRedisTemplate.getConnectionFactory())
                .getConnection()
                .scan(scanOptions);

        List<String> keys = new ArrayList<>();
        while (cursor.hasNext()) {
            byte[] keyBytes = cursor.next();
            keys.add(new String(keyBytes)); // 记得 byte[] -> String
        }
        cursor.close(); // 关闭 cursor，避免连接泄露

        // 累加每个 key 的 zset 元素数量
        long totalUnreadCount = 0;
        for (String key : keys) {
            Long count = stringRedisTemplate.opsForZSet().zCard(key); // zCard = 获取 zset 元素个数
            if (count != null) {
                totalUnreadCount += count;
            }
        }
        return Result.success(totalUnreadCount);
    }

    /**
     * 获取消息队列详情信息
     *
     * @param id 消息队列id
     * @return 消息队列详情信息
     */
    public Result<ChatThreadDetailVO> getChatMessageList(Long id) {
        ShopChatThread shopChatThread = chatThreadRepository.findById(id).get();
        Long currentId = BaseContext.getCurrentId();
        ChatThreadDetailVO chatThreadDetailVO = new ChatThreadDetailVO();
        chatThreadDetailVO.setSenderId(currentId);
        chatThreadDetailVO.setId(id);
        // 封装接收者信息,由于消息队列存储时,该用户可能为接收者,页可能为发送者, 需要判断一下
        if (Objects.equals(shopChatThread.getUserId(), currentId)) {
            chatThreadDetailVO.setReceiverId(shopChatThread.getReceiverId());
            // 删除该用户对该聊天用户的未读信息
            stringRedisTemplate.delete(String.format(MemberConstant.USER_MESSAGE_KEY, currentId, shopChatThread.getReceiverId()));
            // 封装接收者以及发送者是否为店铺客服
            if (shopChatThread.getReceiverStoreId() != null) {
                // 接收者是一个客服,封装店铺信息
                chatThreadDetailVO.setReceiverStoreId(shopChatThread.getReceiverStoreId());
                chatThreadDetailVO.setSenderStoreId(shopChatThread.getSenderStoreId());
            } else {
                // 接收者为一个用户,封装用户信息
                chatThreadDetailVO.setReceiverId(shopChatThread.getReceiverId());
                chatThreadDetailVO.setSenderStoreId(shopChatThread.getSenderStoreId());
            }
            // 封装发送者信息
            if (shopChatThread.getSenderStoreId() != null) {
                ShopStore shopStore = storeRepository.findById(shopChatThread.getSenderStoreId()).get();
                chatThreadDetailVO.setName(shopStore.getName());
                chatThreadDetailVO.setAvatar(shopStore.getAvatar());
            } else {
                ShopMember shopMember = memberRepository.findById(currentId).get();
                chatThreadDetailVO.setName(shopMember.getNickname());
                chatThreadDetailVO.setAvatar(shopMember.getAvatar());
            }
        } else if (Objects.equals(shopChatThread.getReceiverId(), currentId)) {
            chatThreadDetailVO.setReceiverId(shopChatThread.getUserId());
            // 删除该用户对该聊天用户的未读信息
            stringRedisTemplate.delete(String.format(MemberConstant.USER_MESSAGE_KEY, currentId, shopChatThread.getUserId()));
            // 封装接收者以及发送者是否为店铺客服
            if (shopChatThread.getSenderStoreId() != null) {
                // 接收者是一个客服,封装店铺信息
                chatThreadDetailVO.setReceiverStoreId(shopChatThread.getSenderStoreId());
                chatThreadDetailVO.setSenderStoreId(shopChatThread.getReceiverStoreId());
            } else {
                // 接收者为一个用户,封装用户信息
                chatThreadDetailVO.setReceiverId(shopChatThread.getUserId());
                chatThreadDetailVO.setSenderStoreId(shopChatThread.getReceiverStoreId());
            }
            // 封装发送者信息
            if (shopChatThread.getReceiverStoreId() != null) {
                ShopStore shopStore = storeRepository.findById(shopChatThread.getReceiverStoreId()).get();
                chatThreadDetailVO.setName(shopStore.getName());
                chatThreadDetailVO.setAvatar(shopStore.getAvatar());
            } else {
                ShopMember shopMember = memberRepository.findById(currentId).get();
                chatThreadDetailVO.setName(shopMember.getNickname());
                chatThreadDetailVO.setAvatar(shopMember.getAvatar());
            }
        }
        // 封装消息队列消息列表
        List<ChatMessageVO> chatMessageList = shopChatThread.getShopChatMessages()
                .stream()
                .map(shopChatMessage -> {
                    ChatMessageVO chatMessageVO = BeanUtil.copyProperties(shopChatMessage, ChatMessageVO.class);
                    chatMessageVO.setUserId(shopChatMessage.getSenderId());
                    if (shopChatMessage.getStoreId() != null) {
                        // 封装发送者信息,封装店铺信息
                        ShopStore shopStore = storeRepository.findById(shopChatMessage.getStoreId()).get();
                        chatMessageVO.setName(shopStore.getName());
                        chatMessageVO.setAvatar(shopStore.getAvatar());
                    } else {
                        ShopMember shopMember = memberRepository.findById(shopChatMessage.getSenderId()).get();
                        chatMessageVO.setName(shopMember.getNickname());
                        chatMessageVO.setAvatar(shopMember.getAvatar());
                    }
                    return chatMessageVO;
                })
                .toList();
        chatThreadDetailVO.setChatMessageList(chatMessageList);
        return Result.success(chatThreadDetailVO);
    }

    /**
     * 发送消息
     *
     * @param chatMessageDTO 消息信息相关对象
     */
    public void sendChatMessage(ChatMessageDTO chatMessageDTO) {
        // 判断是否是否连接websocket
        Long receiverId = chatMessageDTO.getReceiverId();
        boolean isOnline = myWebSocketHandler.isOnline(receiverId.toString());
        // 发送的信息
        String message = chatMessageDTO.getMessage();
        // 发送信息用户id
        Long userId = chatMessageDTO.getUserId();
        if (isOnline) {
            ChatMessageVO chatMessageVO = new ChatMessageVO();
            // 将websocket发送的消息id设置成消息队列的id,便于前端添加消息
            chatMessageVO.setId(chatMessageDTO.getId());
            chatMessageVO.setUserId(userId);
            chatMessageVO.setReceiverId(receiverId);
            chatMessageVO.setMessage(message);
            chatMessageVO.setIsRead(false);
            chatMessageVO.setAvatar(chatMessageDTO.getAvatar());
            chatMessageVO.setName(chatMessageDTO.getName());
            String messageJsonStr = JSONUtil.toJsonStr(chatMessageVO);
            log.info("webSocket sendMessage:{}", message);
            myWebSocketHandler.sendMessageToUser(receiverId.toString(), messageJsonStr);
        } else {
            stringRedisTemplate.opsForZSet().add(String.format(MemberConstant.USER_MESSAGE_KEY, receiverId, userId), message, System.currentTimeMillis());
        }
        ShopChatThread chatThread = chatThreadRepository.findById(chatMessageDTO.getId()).get();
        chatThread.setLastMessage(message);
        chatThread.setLastMessageTime(LocalDateTime.now());
        List<ShopChatMessage> shopChatMessageList = chatThread.getShopChatMessages();
        // 封装消息信息结果
        ShopChatMessage shopChatMessage = getShopChatMessages(chatMessageDTO.getSenderStoreId(), userId, receiverId, chatThread, message);
        shopChatMessageList.add(shopChatMessage);
        chatThread.setShopChatMessages(shopChatMessageList);
        chatThreadRepository.saveAndFlush(chatThread);
    }

    // 封装聊天信息对象并返回
    private static ShopChatMessage getShopChatMessages(Long storeId, Long userId, Long receiverId, ShopChatThread shopChatThread, String message) {
        ShopChatMessage shopChatMessage = new ShopChatMessage();
        shopChatMessage.setStoreId(storeId);
        shopChatMessage.setReceiverId(receiverId);
        shopChatMessage.setIsRead(false);
        shopChatMessage.setDeleted(false);
        shopChatMessage.setMessage(message);
        shopChatMessage.setSenderId(userId);
        shopChatMessage.setShopChatThread(shopChatThread);
        return shopChatMessage;
    }
}
