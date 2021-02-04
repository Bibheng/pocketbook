package com.matthew.pocketbook.common.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * 项目常量类
 *
 * @author Matthew
 * @date 2021-01-28 17:28
 **/
public class Constant {
    /**
     * 请求状态码：未登录
     */
    public static final int UNAUTHORIZED_CODE = -1;
    /**
     * 请求状态码：成功
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 请求状态码：失败
     */
    public static final int ERROR_CODE = 500;
    /**
     * token过期时间 2小时
     */
    public static final long SHORT_EXPIRE_TIME = 2 * 60 * 60 * 1000;
    /**
     * token过期时间 30天
     */
    public static final long LONG_EXPIRE_TIME = 30L * 24 * 60 * 60 * 1000;
    /**
     * token密钥
     */
    @Value("jwtSecret")
    public static final String SECRET = "";
}
