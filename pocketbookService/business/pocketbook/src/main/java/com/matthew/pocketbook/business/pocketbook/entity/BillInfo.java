package com.matthew.pocketbook.business.pocketbook.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单清单
 *
 * @author songzeheng
 * @date 2023/3/27 23:51
 **/
@Data
public class BillInfo {
    private String billId;
    private String billType;
    private BigDecimal money;
    private Date createdDate;
    private String createdBy;
    private String payer;
    private String payee;
    private String payType;
    private String payDate;
}
