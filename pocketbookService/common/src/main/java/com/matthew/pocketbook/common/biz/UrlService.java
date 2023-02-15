package com.matthew.pocketbook.common.biz;

/**
 * URL通用服务类
 *
 * @author songzeheng
 * @date 2022/10/12 23:34
 **/
public interface UrlService {
    /**
     * 判断当前url是否是公用url
     *
     * @param requestUrl    url
     * @param requestMethod method
     * @return
     */
    boolean isPublicUrl(String requestUrl, String requestMethod);
}
