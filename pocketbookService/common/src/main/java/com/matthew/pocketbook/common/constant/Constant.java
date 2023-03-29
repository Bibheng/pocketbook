package com.matthew.pocketbook.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 项目常量类
 *
 * @author Matthew
 * @date 2021-01-28 17:28
 **/
@Component
public class Constant {

    /*环境相关*/
    /**
     * 是否为开发环境
     */
    public static boolean IS_DEV = false;
    @Value("${spring.profiles.active}")
    public void setIsDev(String active) {
        IS_DEV = active.contains("dev");
    }

    /*日志相关*/
    /**
     * MDC中用户id的key
     */
    public static final String MDC_USER_ID = "userId";

    /**
     * MDC中请求id的key
     */
    public static final String MDC_REQUEST_ID = "requestId";

    /*请求码相关*/
    /**
     * 请求状态码：未登录
     */
    public static final int UNAUTHORIZED_CODE = -1;
    /**
     * 请求状态码：成功
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 请求状态码：服务器异常
     */
    public static final int ERROR_CODE = 500;

    /*登录相关*/
    /**
     * token过期时间 2小时
     */
    public static final long SHORT_EXPIRE_TIME = 2 * 60 * 60 * 1000;
    /**
     * token过期时间 30天
     */
    public static final long LONG_EXPIRE_TIME = 30L * 24 * 60 * 60 * 1000;
    /**
     * 重置密码验证码过期时间
     */
    public static final int RESET_PASSWORD_EXPIRE_TIME = 15 * 60 * 1000;
    /**
     * jwt key
     */
    public static final String JWT_KEY = "jwt-token";
    /**
     * token密钥
     */
    public static String SECRET = "";
    @Value("${jwtSecret}")
    public void setSecret(String secret) {
        SECRET = secret;
    }

    /*redis相关*/
    /**
     * redis 默认过期时间
     */
    public static final int DEFAULT_EXPIRE_TIME = 60 * 1000;

    /*url相关*/
    /**
     * url公共前缀
     */
    public static String URL_PREFIX;
    @Value("${server.servlet.context-path}")
    public void setUrlPrefix(String urlPrefix){
        URL_PREFIX = urlPrefix;
    }
}
