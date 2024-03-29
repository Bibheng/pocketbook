package com.matthew.pocketbook.business.user.controller;

import com.matthew.pocketbook.business.user.entity.LoginParam;
import com.matthew.pocketbook.business.user.entity.RegisterParam;
import com.matthew.pocketbook.business.user.service.UserService;
import com.matthew.pocketbook.common.annotation.Group;
import com.matthew.pocketbook.common.entity.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户控制器
 *
 * @author Matthew
 * @date 2021-01-28 17:12
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginParam loginParam) {
        return Result.success(userService.login(loginParam));
    }

    @PutMapping("")
    public Result register(@RequestBody @Validated({Group.Add.class}) RegisterParam registerParam) {
        userService.register(registerParam);
        return Result.success(null);
    }

    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody @Validated(Group.Update.class) RegisterParam resetParam) {
        userService.resetPassword(resetParam);
        return Result.success(null);
    }

    @GetMapping("/authCode")
    public Result sendAuthCode(@Param("email") String email) {
        userService.sendAuthCode(email);
        return Result.success(null);
    }

}
