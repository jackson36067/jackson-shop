package com.jackson.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeFormatUtils {

    /**
     * 格式化localDateTime类型时间
     *
     * @param dateTime 时间
     * @return string
     */
    public static String getTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "未知时间";
        }
        LocalDateTime now = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(dateTime, now);

        if (days < 1) {
            return "今日";
        } else if (days == 1) {
            return "昨天";
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

    /**
     * 格式化localDate类型时间
     *
     * @param date 要格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        if (date.equals(today)) {
            return "今天";
        } else if (date.equals(yesterday)) {
            return "昨天";
        } else if (date.getYear() == today.getYear()) {
            // 今年内的日期，显示 "MM月dd日"
            return date.format(DateTimeFormatter.ofPattern("MM月dd日"));
        } else {
            // 超出今年的日期，显示 "yyyy年MM月dd日"
            return date.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        }
    }

    /**
     * 规格化时间
     *
     * @param dateTime
     * @return
     */
    public static String formatTime(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();

        // 今天 -> 返回具体小时以及分钟
        if (ChronoUnit.DAYS.between(dateTime, now) == 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return dateTime.format(formatter);
        }

        // 昨天
        if (ChronoUnit.DAYS.between(dateTime, now) == 1) {
            return "昨天";
        }

        // 超过两天但小于一个月
        if (ChronoUnit.DAYS.between(dateTime, now) < 30) {
            long days = ChronoUnit.DAYS.between(dateTime, now);
            return days + " 天前";
        }

        // 超过一个月但小于一年
        if (ChronoUnit.MONTHS.between(dateTime, now) < 12) {
            long months = ChronoUnit.MONTHS.between(dateTime, now);
            return months + " 个月前";
        }

        // 超过一年
        long years = ChronoUnit.YEARS.between(dateTime, now);
        return years + " 年前";
    }
}
