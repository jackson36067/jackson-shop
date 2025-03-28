package com.jackson;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

// 密码进行MD5加密
//@SpringBootTest
public class PasswordMD5 {

    @Test
    public void test() {
        String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println(md5Password);
    }
}
