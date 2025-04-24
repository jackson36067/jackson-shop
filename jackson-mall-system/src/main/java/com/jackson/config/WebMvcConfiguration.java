package com.jackson.config;

import com.jackson.interceptor.JwtUserInterceptor;
import com.jackson.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtUserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/category/list",
                        "/api/member/login",
                        "/api/member/code",
                        "/api/column/list",
                        "/api/column/detail/**",
                        "/api/goods",
                        "/api/goods/category/**",
                        "/api/goods/sku/**",
                        "/api/goods/like",
                        "/ws/**"
                );
    }

    /**
     * 拓展Spring MVC框架消息转换器
     * 将所有时间类型的数据格式化
     *
     * @param converters the list of configured converters to be extended
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("拓展消息转换器");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置一个对象转换器给消息转换器,用于将java对象序列化为json格式的数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己定义的消息转换器添加到容器中
        //参数0是为了设置优先情况,把我们自定义的消息转换器设置为最优先的
        converters.add(0, converter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // 允许所有路径
                .allowedOrigins("http://localhost:3000")  // 允许所有域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的方法
                .allowedHeaders("*")  // 允许所有请求头
                .allowCredentials(true)  // 允许携带 Cookie
                .maxAge(3600);  // 预检请求的缓存时间
    }
}
