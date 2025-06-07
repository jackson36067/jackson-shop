package com.jackson.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置rabbitmq消费者消费消息重试次数超过最后次数后的消息处理机制
 * 将消息转发到另一个交换机
 */
@Configuration
// 配置生效的条件, 配置类中需要启动消费值消费消息重试机制
@ConditionalOnProperty(prefix = "spring.rabbit.listener.simple.retry", name = "enabled", havingValue = "true")
public class RabbitMQErrorConfiguration {
    // 配置接收消息的新的交换机以及队列
    @Bean
    public DirectExchange errorExchange() {
        return new DirectExchange("error.direct");
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("error.queue");
    }

    @Bean
    public Binding errorBinding() {
        return BindingBuilder
                .bind(errorQueue())
                .to(errorExchange())
                .with("error");
    }

    /**
     * 配置消息接收机制 -> 重新发送到另一个交换机
     *
     * @param rabbitTemplate
     * @return
     */
    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, "error.direct", "error");
    }
}
