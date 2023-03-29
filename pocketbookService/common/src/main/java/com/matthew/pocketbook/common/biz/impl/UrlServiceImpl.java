package com.matthew.pocketbook.common.biz.impl;

import com.matthew.pocketbook.common.biz.UrlService;
import com.matthew.pocketbook.common.mapper.UrlMapper;
import com.matthew.pocketbook.common.entity.Url;
import com.matthew.pocketbook.common.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * URL通用服务实现类
 *
 * @author songzeheng
 * @date 2022/10/12 23:34
 **/
@Service
public class UrlServiceImpl implements UrlService {
    private static volatile List<Url> publicUrl;

    @Autowired
    private UrlMapper urlMapper;

    private static AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean isPublicUrl(String requestUrl, String requestMethod) {

        List<Url> publicUrl = getPublicUrl();
        for (Url url : publicUrl) {
            if (url.getMethod().equalsIgnoreCase(requestMethod) && matcher.match(url.getUrl(), requestUrl)) {
                return true;
            }
        }
        return false;
    }

    private List<Url> getPublicUrl() {
        if (publicUrl == null) {
            synchronized (LoginFilter.class) {
                if (publicUrl == null) {
                    publicUrl = urlMapper.getPublicUrl();
                }
            }
        }
        return publicUrl;
    }

}
