package com.jackson.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ消息转换器
 */
@Configuration
public class JsonMessageConfig {
    /**
     * 使用json消息转换器
     * @return
     */
    @Bean
    public MessageConverter JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
