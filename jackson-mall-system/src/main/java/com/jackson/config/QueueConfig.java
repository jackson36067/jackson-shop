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
    @Bean
    public Queue genObjectQueue() {
        return QueueBuilder.durable(RabbitMQConstant.QUEUE_KEY).build();
    }
}
