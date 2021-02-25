package com.matthew.pocketbook.common.util;

import com.matthew.pocketbook.common.entity.UserContext;

/**
 * 用户上下文threadLocal
 *
 * @author Matthew
 * @date 2021-02-25 17:25
 **/
public class UserContextHolder {

    private static final ThreadLocal<UserContext> USER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static UserContext get() {
        return USER_CONTEXT_THREAD_LOCAL.get();
    }

    public static void set(UserContext context) {
        USER_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static void remove() {
        USER_CONTEXT_THREAD_LOCAL.remove();
    }
}
