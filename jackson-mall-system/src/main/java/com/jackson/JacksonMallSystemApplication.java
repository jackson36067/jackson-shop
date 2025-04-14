package com.jackson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JacksonMallSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JacksonMallSystemApplication.class, args);
    }

}
