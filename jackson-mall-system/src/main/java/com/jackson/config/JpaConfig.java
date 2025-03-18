package com.jackson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing // 开启springDataJpa的审计功能
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new MyAuditorAware();
    }
}
