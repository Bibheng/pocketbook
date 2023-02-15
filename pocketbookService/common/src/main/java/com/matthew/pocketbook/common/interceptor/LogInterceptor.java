package com.matthew.pocketbook.common.interceptor;

import com.matthew.pocketbook.common.constant.Constant;
import com.matthew.pocketbook.common.util.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 *
 * @author songzeheng
 * @date 2022/10/12 23:34
 **/
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = StringUtil.getUUID(false);
//        MDC.put(Constant.MDC_USER_ID, )
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
