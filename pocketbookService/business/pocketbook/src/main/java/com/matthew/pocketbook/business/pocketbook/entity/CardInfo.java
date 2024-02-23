package com.matthew.pocketbook.business.pocketbook.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 账单卡片
 *
 * @author songzeheng
 * @date 2024/2/2 17:31
 **/
@Data
@Builder
public class CardInfo {
    /**
     * 当日支出
     */
    private BigDecimal currentDayPay;
    /**
     * 当日支出较昨天
     */
    private BigDecimal compareDayPay;
    /**
     * 当月支出
     */
    private BigDecimal currentMonthPay;
    /**
     * 当月支持较上月
     */
    private BigDecimal compareMonthPay;
    /**
     * 当月收入
     */
    private BigDecimal currentMonthIncome;
    /**
     * 当月收入较上月
     */
    private BigDecimal compareMonthIncome;
}
