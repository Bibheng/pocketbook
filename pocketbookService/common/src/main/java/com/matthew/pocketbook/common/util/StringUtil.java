package com.matthew.pocketbook.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author Matthew
 * @date 2021-01-29 10:42
 **/
public class StringUtil {
    private static char[][] chars = {{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}
        , {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}};

    private static Random rand = new Random();

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

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @param mode   字符串模式 0/数字字符混合，1/字符，2/数字
     * @return java.lang.String
     * @author Matthew
     * @date 2021-02-22 17:10
     */
    public static String getRandomString(int length, int mode) {
        StringBuilder sb = new StringBuilder(length);
        char[] temp = chars[mode];
        for (int i = 0; i < length; i++) {
            sb.append(temp[getRandomNumber(0, temp.length - 1)]);
        }
        return sb.toString();
    }

    /**
     * 生成区间内随机数字
     *
     * @param min 最小值
     * @param max 最大值
     * @return int
     * @author Matthew
     * @date 2021-02-22 17:15
     */
    public static int getRandomNumber(int min, int max) {
        return (int) (min + rand.nextInt(max - min + 1));
    }

    /**
     * 生成UUID
     * @param isContainsHyphen true带连接符，false不带连接符
     * @return
     */
    public static String getUUID(boolean isContainsHyphen) {
        return isContainsHyphen ? UUID.randomUUID().toString() : UUID.randomUUID().toString().replace("-", "");
    }
}
