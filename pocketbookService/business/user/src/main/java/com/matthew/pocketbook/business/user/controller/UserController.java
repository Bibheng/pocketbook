package com.matthew.pocketbook.business.user.controller;

import com.matthew.pocketbook.common.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Matthew
 * @date 2021-01-28 17:12
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    public Result login() {
        return Result.success(null);
    }

}
