package com.jackson.hanlder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    static Map<String, WebSocketSession> userSessionMap = new ConcurrentHashMap<>();

    /**
     * 用户建立连接
     *
     * @param session websocket Session会话
     */
    public void afterConnectionEstablished(WebSocketSession session) {
        // 使用登录用户的id作为key,保证websocket连接
        String userId = extractUserIdFromUri(session);
        userSessionMap.put(userId, session);
        log.info("connect to websocket userId:{}", userId);
    }

    /**
     * 接收到消息
     *
     * @param session websocket session会话
     * @param message 消息
     * @throws Exception 接收消息时抛出的异常
     */

    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 处理接收到的消息
        log.info("Receive message:{}", message.getPayload());
        session.sendMessage(message);
    }

    /**
     * 连接出错
     *
     * @param session   websocket session会话
     * @param exception 连接过程中出现的异常
     * @throws Exception 连接过程中抛出的异常
     */
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Error occurred in websocket connection:{}", exception.getMessage());
    }

    /**
     * 关闭连接
     *
     * @param session     websocket session会话
     * @param closeStatus 关闭状态
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        String userId = extractUserIdFromUri(session);
        log.info("closed websocket connection:{}", userId);
        log.info("close reason:{}", closeStatus.getReason());
    }

    /**
     * 是否支持部分消息
     *
     * @return 不支持
     */
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 从 WebSocket session URI 中提取 userId
     *
     * @param session WebSocket 会话
     * @return 提取到的 userId
     */
    private String extractUserIdFromUri(WebSocketSession session) {
        // 从 session.getUri() 获取路径参数 (如果 URL 是 /ws/{userId} 格式)
        String uri = session.getUri().toString();
        // 假设 URI 为 ws://localhost:8080/ws/{userId}
        String userId = uri.substring(uri.lastIndexOf("/") + 1);  // 提取 {userId}
        return userId;
    }

    // 向特定用户发送消息
    public void sendMessageToUser(String sessionId, String message) {
        WebSocketSession session = userSessionMap.get(sessionId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 判断用户是否连接websocket
    public boolean isOnline(String userId) {
        WebSocketSession session = userSessionMap.get(userId);
        return session != null && session.isOpen();
    }
}
