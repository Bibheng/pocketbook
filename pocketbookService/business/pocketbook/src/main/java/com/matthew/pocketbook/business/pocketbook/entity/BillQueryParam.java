package com.matthew.pocketbook.business.pocketbook.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "账单查询对象")
@Data
public class BillQueryParam {
    @ApiModelProperty(value = "账单id-支持多条", example = "[1, 2]")
    private List<String> billIds;
    @ApiModelProperty(value = "账单类型，01支出/02收入", example = "01")
    private String billType;
    @ApiModelProperty(value = "金额左区间", example = "100")
    private BigDecimal moneyBegin;
    @ApiModelProperty(value = "金额右区间", example = "500")
    private BigDecimal moneyEnd;
    @ApiModelProperty(value = "创建时间左区间", example = "2023-01-01 00:00:00")
    private Date createdDateBegin;
    @ApiModelProperty(value = "创建时间右区间", example = "2024-01-01 00:00:00")
    private Date createdDateEnd;
    @ApiModelProperty(value = "创建人-支持多条", example = "[szh,abc]")
    private List<String> createdBys;
    @ApiModelProperty(value = "创建人-支持模糊", example = "ab")
    private String createdBy;
    @ApiModelProperty(value = "付款人-支持多条", example = "[szh,abc]")
    private List<String> payers;
    @ApiModelProperty(value = "付款人-支持模糊", example = "ab")
    private String payer;
    @ApiModelProperty(value = "收款人-支持多条", example = "[szh,abc]")
    private List<String> payees;
    @ApiModelProperty(value = "收款人-支持模糊", example = "ab")
    private String payee;
    @ApiModelProperty(value = "账单分类-支持多条", example = "[1, 2]")
    private List<String> payTypes;
    @ApiModelProperty(value = "支付首款日期左区间", example = "2023-01-01")
    private String payDateBegin;
    @ApiModelProperty(value = "支付首款日期右区间", example = "2024-01-01")
    private String payDateEnd;
}

