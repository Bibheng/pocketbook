package com.matthew.pocketbook.common.util;

import com.alibaba.fastjson.JSON;
import com.matthew.pocketbook.common.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 *
 * @author Matthew
 * @date 2021-02-07 14:36
 **/
@Component
public class RedisUtil {
    private static StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.redisTemplate = stringRedisTemplate;
    }

    /**
     * 设置指定过期时间的redis
     *
     * @param key        key
     * @param value      value
     * @param expireTime 过期时间 单位毫秒
     * @author Matthew
     * @date 2021-02-22 16:13
     */
    public static void set(String key, String value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置默认过期时间的redis，默认过期时间为1分钟
     *
     * @param key   key
     * @param value value
     * @author Matthew
     * @date 2021-02-22 16:14
     */
    public static void set(String key, String value) {
        set(key, value, CommonConstant.DEFAULT_EXPIRE_TIME);
    }

    /**
     * 按key删除指定redis
     *
     * @param key key
     * @author Matthew
     * @date 2021-02-22 16:15
     */
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 返回指定类型的redis缓存对象
     *
     * @param key key
     * @param tt  对象类型
     * @return T redis缓存对象
     * @author Matthew
     * @date 2021-02-22 16:15
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Class<T> tt) {
        String str = redisTemplate.opsForValue().get(key);
        if (StringUtil.isEmpty(str)) {
            return null;
        } else {
            if (String.class.getTypeName().equals(tt.getTypeName())) {
                return (T) str;
            } else {
                return JSON.parseObject(str, tt);
            }
        }

    }

}
