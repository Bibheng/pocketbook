package com.matthew.pocketbook.business.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author Matthew
 * @date 2021-01-28 22:51
 **/
@Data
@Accessors(chain = true)
@Builder
public class User {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;
    /**
     * 用户email
     */
    private String email;
    /**
     * 创建时间
     */
    private long createdTime;
    /**
     * 最新登录时间
     */
    private long lastLoginTime;
}
