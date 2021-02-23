package com.matthew.pocketbook.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密算法
 *
 * @author Matthew
 * @date 2021-01-29 10:26
 **/
public class HashUtil {
    /**
     * md5加密
     *
     * @param str 要加密的字符串
     * @return java.lang.String 32位
     * @author Matthew
     * @date 2021-01-29 10:47
     */
    public static String md5(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        return DigestUtils.md5Hex(str);
    }

    /**
     * sha1加密
     *
     * @param str 要加密的字符串
     * @return java.lang.String 40位
     * @author Matthew
     * @date 2021-01-29 10:47
     */
    public static String sha1(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        return DigestUtils.sha1Hex(str);
    }

}
