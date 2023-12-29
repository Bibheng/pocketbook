package com.matthew.pocketbook.business.user.controller;

import com.matthew.pocketbook.business.user.entity.LoginParam;
import com.matthew.pocketbook.business.user.entity.LoginRes;
import com.matthew.pocketbook.business.user.entity.RegisterParam;
import com.matthew.pocketbook.business.user.service.LoginService;
import com.matthew.pocketbook.common.annotation.Group;
import com.matthew.pocketbook.common.entity.Result;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author Matthew
 * @date 2021-01-28 17:12
 **/
@Api(tags = "登录相关类接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "登录")
    @ApiResponse(code = 200, message = "", response = LoginRes.class)
    @PostMapping("")
    public Result<LoginRes> login(@RequestBody LoginParam loginParam) {
        return Result.success(loginService.login(loginParam));
    }

    @ApiOperation(value = "注册")
    @PutMapping("register")
    public Result register(@RequestBody @Validated({Group.Add.class}) RegisterParam registerParam) {
        loginService.register(registerParam);
        return Result.success(null);
    }

    @ApiOperation(value = "重置密码")
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody @Validated(Group.Update.class) RegisterParam resetParam) {
        loginService.resetPassword(resetParam);
        return Result.success(null);
    }

    @ApiOperation(value = "发送验证码")
    @GetMapping("/authCode")
    public Result sendAuthCode(@Param("email") @ApiParam("邮箱") String email) {
        loginService.sendAuthCode(email);
        return Result.success(null);
    }

}
