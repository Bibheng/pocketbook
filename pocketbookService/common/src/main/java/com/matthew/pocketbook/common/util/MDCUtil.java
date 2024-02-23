package com.matthew.pocketbook.common.util;

import com.matthew.pocketbook.common.constant.CommonConstant;
import org.slf4j.MDC;

/**
 * MDC操作处理类
 *
 * @author songzeheng
 * @date 2023/3/29 00:46
 **/
public class MDCUtil {
    public static void putUserId(String userId) {
        MDC.put(CommonConstant.MDC_USER_ID, userId);
    }
    public static void putRequestId(String requestId) {
        MDC.put(CommonConstant.MDC_REQUEST_ID, requestId);
    }

    public static void removeUserId() {
        MDC.remove(CommonConstant.MDC_USER_ID);
    }
    public static void removeRequestId() {
        MDC.remove(CommonConstant.MDC_REQUEST_ID);
    }

    public static String getUserId() {
        return MDC.get(CommonConstant.MDC_USER_ID);
    }

    public static String getRequestId() {
        return MDC.get(CommonConstant.MDC_REQUEST_ID);
    }

    public static String get(String key) {
        return MDC.get(key);
    }

    public static String get(String key, String defaultValue) {
        String value = MDC.get(key);
        return value == null ? defaultValue : value;
    }

    public static void put(String key, String value) {
        MDC.put(key, value);
    }

    public static void clear() {
        MDC.clear();
    }

    public static void remove(String key) {
        MDC.remove(key);
    }

}
