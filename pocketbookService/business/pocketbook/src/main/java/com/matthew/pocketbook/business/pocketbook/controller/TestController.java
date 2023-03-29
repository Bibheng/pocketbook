package com.matthew.pocketbook.business.pocketbook.controller;

import com.matthew.pocketbook.business.pocketbook.entity.BillQueryParam;
import com.matthew.pocketbook.business.pocketbook.service.BillService;
import com.matthew.pocketbook.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台接口测试类
 *
 * @author songzeheng
 * @date 2023/3/27 23:41
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private BillService billService;

    @PostMapping("/getBillList")
    public Result getBillList(@RequestBody BillQueryParam param) {
        return Result.success(billService.getBillList(param));
    }

}
