package com.matthew.pocketbook.business.user.entity;

import com.matthew.pocketbook.common.annotation.Group;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "用户名不能为空", groups = {Group.Add.class, Group.Update.class})
    private String userName;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Group.Add.class, Group.Update.class})
    private String password;
    /**
     * 邮箱
     */
    @NotBlank(message = "用户邮箱不能为空", groups = {Group.Add.class, Group.Update.class})
    private String email;
    /**
     * 邮箱验证码
     */
    @NotBlank(message = "验证码不能为空", groups = {Group.Update.class})
    private String authCode;

}
