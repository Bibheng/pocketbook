package com.matthew.pocketbook.business.pocketbook.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 账单查询对象
 *
 * @author songzeheng
 * @date 2023/3/27 23:46
 **/
@Data
public class BillQueryParam {
    private List<String> billIds;
    /**
     * 01支出/02收入
     */
    private String billType;
    private BigDecimal moneyBegin;
    private BigDecimal moneyEnd;
    private Date createdDateBegin;
    private Date createdDateEnd;
    private List<String> createdBys;
    /**
     * 单个创建人查询，支持模糊匹配
     */
    private String createdBy;
    private List<String> payers;
    /**
     * 单个付款人查询，支持模糊匹配
     */
    private String payer;
    private List<String> payees;
    /**
     * 单个收款人查询，支持模糊匹配
     */
    private String payee;
    private List<String> payTypes;
    private String payDateBegin;
    private String payDateEnd;
}
