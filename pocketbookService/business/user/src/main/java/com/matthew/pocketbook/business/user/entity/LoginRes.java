package com.matthew.pocketbook.business.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("登录返回信息")
public class LoginRes {
    /**
     * token标识
     */
    @ApiModelProperty("token标识")
    private String token;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private int userId;
    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String userName;
    /**
     * 用户email
     */
    @ApiModelProperty("用户email")
    private String email;
    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    private String lastLoginTime;
}
