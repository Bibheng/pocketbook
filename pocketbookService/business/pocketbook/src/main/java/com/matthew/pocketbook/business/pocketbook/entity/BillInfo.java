package com.matthew.pocketbook.business.pocketbook.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单清单
 *
 * @author songzeheng
 * @date 2023/3/27 23:51
 **/
@ApiModel(value = "账单信息对象")
@Data
public class BillInfo {
    @ApiModelProperty(value = "账单id", example = "1")
    private String billId;
    @ApiModelProperty(value = "账单类型，01支出/02收入", example = "01")
    private String billType;
    @ApiModelProperty(value = "金额", example = "100")
    private BigDecimal money = new BigDecimal(0);
    @ApiModelProperty(value = "创建时间", example = "2023-01-01")
    private Date createdDate;
    @ApiModelProperty(value = "创建人", example = "szh")
    private String createdBy;
    @ApiModelProperty(value = "付款人", example = "szh")
    private String payer;
    @ApiModelProperty(value = "收款人", example = "szh")
    private String payee;
    @ApiModelProperty(value = "账单分类", example = "1")
    private String payType;
    @ApiModelProperty(value = "支付日期", example = "2023-01-01")
    private String payDate;
    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;
}
