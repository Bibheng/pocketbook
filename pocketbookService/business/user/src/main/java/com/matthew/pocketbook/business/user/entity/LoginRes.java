package com.matthew.pocketbook.business.user.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录接口返回
 *
 * @author Matthew
 * @date 2021-01-28 22:37
 **/
@Data
@Accessors(chain = true)
@Builder
public class LoginRes {
    /**
     * token标识
     */
    private String token;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户email
     */
    private String email;
    /**
     * 上次登录时间
     */
    private String lastLoginTime;
}
