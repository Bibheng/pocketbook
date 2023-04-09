package com.matthew.pocketbook.business.user.entity;

import lombok.Data;

/**
 * 注册参数类
 *
 * @author Matthew
 * @date 2021-02-01 14:22
 **/
@Data
public class RegisterParam {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱验证码
     */
    private String authCode;

}
