package com.jackson.config;

import com.jackson.context.BaseContext;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 通知springdatajpa当前用户是谁 (用户createBy以及updateBy的公共字段自动填充)
 */
public class MyAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            return Optional.empty();
        }
        return Optional.of(userId.toString());
    }
}
