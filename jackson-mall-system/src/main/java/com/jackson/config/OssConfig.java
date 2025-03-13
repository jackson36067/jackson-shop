package com.jackson.config;

import com.jackson.properties.AliOssProperties;
import com.jackson.utils.AliOssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OssConfig {
    @Bean
    @ConditionalOnMissingBean //当IOC容器中有bean时,不会再去创建他的对象
    public AliOssUtils aliOssUtils(AliOssProperties aliOssProperties) {
        log.info("开始创建AliOSSUtils对象");
        return new AliOssUtils(aliOssProperties.getEndpoint()
                , aliOssProperties.getAccessKeyId()
                , aliOssProperties.getAccessKeySecret()
                , aliOssProperties.getBucketName()
        );

    }
}