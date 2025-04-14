package com.jackson.task;

import com.jackson.repository.MemberCouponRepository;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 监听用户领取优惠卷是否过期
 */
@Component
public class MemberCouponTask {

    @Resource
    private MemberCouponRepository memberCouponRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void listenMemberCouponExpireTime() {
        // 将用户的优惠卷设置为删除状态
        memberCouponRepository.updateAllDelFlagBeforeExpireTime((short) 1, LocalDateTime.now());
    }
}
