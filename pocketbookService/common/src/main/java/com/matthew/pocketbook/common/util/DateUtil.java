package com.matthew.pocketbook.common.util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间工具
 *
 * @author songzeheng
 * @date 2024/2/2 17:48
 **/
public class DateUtil {
    /**
     * 返回当月第一天
     * @param currentDay 2024-01-05
     * @return 2024-01-01
     */
    public static String getFirstDayOnMonth(String currentDay) {
        LocalDate day = LocalDate.parse(currentDay);
        return day.with(TemporalAdjusters.firstDayOfMonth()).toString();
    }

    /**
     * 返回当月最后一天
     * @param currentDay 2024-01-05
     * @return 2024-01-31
     */
    public static String getLastDayOnMonth(String currentDay) {
        LocalDate day = LocalDate.parse(currentDay);
        return day.with(TemporalAdjusters.lastDayOfMonth()).toString();
    }

    /**
     * 上月第一天
     * @param currentDay 2024-01-05
     * @return 2023-12-01
     */
    public static String getFirstDayOnLastMonth(String currentDay) {
        LocalDate day = LocalDate.parse(currentDay);
        return day.plusMonths(-1).with(TemporalAdjusters.firstDayOfMonth()).toString();
    }

    /**
     * 上月最后天
     * @param currentDay 2024-01-05
     * @return 2023-12-31
     */
    public static String getLastDayOnLastMonth(String currentDay) {
        LocalDate day = LocalDate.parse(currentDay);
        return day.plusMonths(-1).with(TemporalAdjusters.lastDayOfMonth()).toString();
    }

    /**
     * 计算间隔日期
     * @param currentDay 当前日期 yyyy-MM-dd
     * @param i 前后间隔天数
     * @return
     */
    public static String getCalDay(String currentDay, int i) {
        LocalDate day = LocalDate.parse(currentDay);
        return day.plusDays(i).toString();
    }
}
