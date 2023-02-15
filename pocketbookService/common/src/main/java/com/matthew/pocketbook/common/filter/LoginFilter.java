package com.matthew.pocketbook.common.filter;

import com.alibaba.fastjson.JSON;
import com.matthew.pocketbook.common.biz.UrlService;
import com.matthew.pocketbook.common.constant.Constant;
import com.matthew.pocketbook.common.entity.Result;
import com.matthew.pocketbook.common.util.JwtUtil;
import com.matthew.pocketbook.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器
 * 放行static静态文件请求，公共请求，其他请求需要校验jwt，验证通过则构建用户上下文
 *
 * @author Matthew
 * @date 2021-02-24 16:06
 **/
@Component
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
@Slf4j
public class LoginFilter implements Filter {

    @Autowired
    UrlService urlService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUrl = request.getRequestURI().replace(Constant.URL_PREFIX, "");

        // 指定允许其他域名访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "0");
        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,OPTIONS,DELETE");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,jwt-token");

        // 1、放行静态文件
        if (requestUrl.startsWith("/static")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 2、放行Options请求
        String requestMethod = request.getMethod();
        if (requestMethod.equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }

        // 3、放行公共请求url 比如登录注册等
        if (urlService.isPublicUrl(requestUrl, requestMethod)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 4、校验jwt是否合法
        try {
            if (JwtUtil.checkUserJwt(request.getHeader(Constant.JWT_KEY))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSON.toJSONString(Result.unAuth()));
            }
        } finally {
            UserContextHolder.remove();// 执行完servlet后释放threadLocal，避免内存溢出
        }
    }

}
