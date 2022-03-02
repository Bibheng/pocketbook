package com.matthew.pocketbook.business.user.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录接口入参
 *
 * @author Matthew
 * @date 2021-01-28 22:32
 **/
@Data
public class LoginParam {
    /**
     * 登录账号，用户名或邮箱
     */
    @NotBlank(message = "登录用户名称不能为空")
    private String loginCode;
    /**
     * 密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;
    /**
     * 长期登录选项
     */
    private boolean rememberMe = false;
}
