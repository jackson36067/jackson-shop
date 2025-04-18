package com.jackson.utils;

import com.jackson.constant.EmailConstant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailUtils {
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 发送邮箱提醒有关任务操作
     *
     * @param to
     * @return
     */
    public void sendMessage(String to, String subject, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(EmailConstant.EMAIL_FROM);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(code);
        try {
            javaMailSender.send(simpleMailMessage);
            log.info("邮件发送成功！");
        } catch (Exception e) {
            log.info("邮件发送失败：{}", e.getMessage());
            throw  new RuntimeException("邮件发送异常");
        }
    }
}
