package com.matthew.pocketbook.business.pocketbook.controller;

import com.matthew.pocketbook.business.pocketbook.entity.BillInfo;
import com.matthew.pocketbook.business.pocketbook.entity.BillQueryParam;
import com.matthew.pocketbook.business.pocketbook.service.BillService;
import com.matthew.pocketbook.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账单接口类
 *
 * @author songzeheng
 * @date 2023/3/27 23:41
 **/
@Api(tags = "账单相关接口")
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @ApiOperation(value = "搜索账单")
    @PostMapping("/getBillList")
    public Result getBillList(@RequestBody BillQueryParam param) {
        return Result.success(billService.getBillList(param));
    }

    @ApiOperation(value = "新增修改账单")
    @PostMapping("/updateBillInfo")
    public Result updateBillInfo(@RequestBody BillInfo param) {
        return Result.success(billService.updateBillInfo(param));
    }

}
