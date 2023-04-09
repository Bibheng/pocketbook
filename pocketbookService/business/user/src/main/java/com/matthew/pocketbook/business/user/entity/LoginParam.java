package com.matthew.pocketbook.business.user.entity;

import lombok.Data;

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
    private String loginCode;
    /**
     * 密码
     */
    private String password;
    /**
     * 长期登录选项
     */
    private boolean rememberMe = false;
}
