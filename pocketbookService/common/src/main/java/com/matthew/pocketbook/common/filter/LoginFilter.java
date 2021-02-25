package com.matthew.pocketbook.common.filter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.matthew.pocketbook.common.constant.Constant;
import com.matthew.pocketbook.common.dao.UrlDao;
import com.matthew.pocketbook.common.entity.Result;
import com.matthew.pocketbook.common.entity.Url;
import com.matthew.pocketbook.common.entity.UserContext;
import com.matthew.pocketbook.common.util.JwtUtil;
import com.matthew.pocketbook.common.util.StringUtil;
import com.matthew.pocketbook.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @Value("server.servlet.context-path")
    private String urlPrefix;

    private static AntPathMatcher matcher = new AntPathMatcher();

    private static volatile List<Url> publicUrl;

    @Autowired
    private UrlDao urlDao;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUrl = request.getRequestURI().replace(urlPrefix, "");

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
        List<Url> publicUrl = getPublicUrl();
        for (Url url : publicUrl) {
            if (url.getMethod().equalsIgnoreCase(requestMethod) && matcher.match(url.getUrl(), requestUrl)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        // 4、校验jwt是否合法
        if (this.checkJwt(request.getHeader(Constant.JWT_KEY))) {
            filterChain.doFilter(servletRequest, servletResponse);
            UserContextHolder.remove();// 执行完servlet后释放threadLocal，避免内存溢出
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(Result.unAuth()));
        }
    }

    private boolean checkJwt(String jwt) {
        if (StringUtil.isEmpty(jwt)) {
            log.error("jwt为空");
            return false;
        }
        try {
            Map<String, Claim> map = JwtUtil.decode(jwt, Constant.SECRET);
            int userId = Integer.parseInt(map.get("userId").asString());
            UserContext context = new UserContext();
            context.setUserId(userId);
            UserContextHolder.set(context);
            return true;
        } catch (Exception e) {
            log.error("jwt解密失败：{},原因：{}", jwt, e.getMessage());
            return false;
        }
    }

    private List<Url> getPublicUrl() {
        if (publicUrl == null) {
            synchronized (LoginFilter.class) {
                if (publicUrl == null) {
                    publicUrl = urlDao.getPublicUrl();
                }
            }
        }
        return publicUrl;
    }
}
