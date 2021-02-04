package com.matthew.pocketbook.common.util;

/**
 * 字符串工具类
 *
 * @author Matthew
 * @date 2021-01-29 10:42
 **/
public class StringUtils {
    /**
     * 判空
     *
     * @param str 字符串
     * @return boolean
     * @author Matthew
     * @date 2021-01-29 10:43
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
