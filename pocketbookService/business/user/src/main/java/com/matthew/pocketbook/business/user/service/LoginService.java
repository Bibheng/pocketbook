package com.matthew.pocketbook.business.user.service;

import com.matthew.pocketbook.business.user.entity.LoginParam;
import com.matthew.pocketbook.business.user.entity.LoginRes;
import com.matthew.pocketbook.business.user.entity.RegisterParam;

/**
 * 登录管理接口
 *
 * @author Matthew
 * @date 2021-01-28 22:27
 **/
public interface LoginService {

    /**
     * 登录接口
     *
     * @param param 登录接口入参
     * @return LoginRes
     * @author Matthew
     * @date 2021-01-28 22:36
     */
    LoginRes login(LoginParam param);
    /**
     * 注册接口
     *
     * @param param 注册接口入参
     * @author Matthew
     * @date 2021-02-03 13:53
     */
    void register(RegisterParam param);

    /**
     * 重置密码接口
     *
     * @param param 重置密码入参
     * @author Matthew
     * @date 2021-02-04 09:51
     */
    void resetPassword(RegisterParam param);

    /**
     * 发送验证码
     *
     * @param email 邮箱
     * @author Matthew
     * @date 2021-02-22 16:44
     */
    void sendAuthCode(String email);
}
