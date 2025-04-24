package com.jackson;

import com.jackson.constant.OrderConstant;
import com.jackson.hanlder.MyWebSocketHandler;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class WebSocketTest {
    @Resource
    private MyWebSocketHandler myWebSocketHandler;

    @Test
    public void testSendMessageToUser() {
        myWebSocketHandler.sendMessageToUser("93", OrderConstant.PLACE_ORDER_MESSAGE);
    }
}
