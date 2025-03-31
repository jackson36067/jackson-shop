package com.jackson;

import com.jackson.constant.RabbitMQConstant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@SpringBootTest
public class LuaTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;

    private static final DefaultRedisScript<Long> COUPON_SCRIPT;

    // 定义读取lua脚本
    static {
        COUPON_SCRIPT = new DefaultRedisScript<>();
        COUPON_SCRIPT.setResultType(Long.class);
        COUPON_SCRIPT.setLocation(new ClassPathResource("coupon.lua"));
    }

    @Test
    public void test() {
        // 想Redis中存入同步优惠卷数量
//        stringRedisTemplate.opsForValue().set("coupon:stock:3", "200");
        stringRedisTemplate.opsForValue().set("coupon:stock:1", "20");
        stringRedisTemplate.opsForValue().set("coupon:stock:2", "20");
    }


    @Test
    public void testGetCoupon() {
        Long result = stringRedisTemplate.execute(COUPON_SCRIPT, Collections.emptyList(), "93", "3");
        if (result == 1) {
            log.info("优惠卷已经被枪光了");
            return;
        }
        if(result == 2) {
            log.info("优惠卷只能枪一遍");
            return;
        }

        // 这时说明用户有领取优惠卷资格, 使用rabbitmq异步处理, 生成用户优惠卷信息,以及减少数据局的库存
        // 为确保Redis预知库存与数据库库存的同步性,可以在删减的同时添加乐观锁以及, 或者消费失败回滚预知库存的数量
        Map<String,Long> info = new HashMap<>();
        info.put("couponId",3L);
        info.put("userId",93L);
        rabbitTemplate.convertAndSend(RabbitMQConstant.COUPON_QUEUE,info);
    }
}
