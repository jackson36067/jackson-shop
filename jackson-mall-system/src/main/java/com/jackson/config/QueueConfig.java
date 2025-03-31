package com.jackson.config;

import com.jackson.constant.RabbitMQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义rabbitMQ队列
 */
@Configuration
public class QueueConfig {

    /**
     * 处理用户关注店铺动作队列
     * @return
     */
    @Bean
    public Queue genObjectQueue() {
        return QueueBuilder.durable(RabbitMQConstant.QUEUE_KEY).build();
    }

    /**
     * 处理保存浏览记录队列
     * @return
     */
    @Bean
    public Queue genBrowseQueue() {
        return QueueBuilder.durable(RabbitMQConstant.BROWSE_QUEUE_KEY).build();
    }

    @Bean
    public Queue genCouponQueue() {
        return QueueBuilder.durable(RabbitMQConstant.COUPON_QUEUE).build();
    }
}
