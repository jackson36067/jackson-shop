package com.jackson.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeFormatUtils {
    public static String getTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "未知时间";
        }

        LocalDateTime now = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(dateTime, now);

        if (days < 1) {
            return "今日";
        } else if (days < 7) {
            return days + "天前";
        } else if (days < 30) {
            return (days / 7) + "周前";
        } else if (days < 365) {
            return (days / 30) + "个月前";
        } else {
            return (days / 365) + "年前";
        }
    }
}
