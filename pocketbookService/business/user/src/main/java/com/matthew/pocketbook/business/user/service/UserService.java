package com.matthew.pocketbook.business.user.service;

import com.matthew.pocketbook.business.user.entity.User;

/**
 * 用户通用服务类
 *
 * @author songzeheng
 * @date 2022/10/12 23:34
 **/
public interface UserService {

    /**
     * 根据用户id或用户邮箱获取用户对象
     *
     * @param userId    用户id
     * @param userEmail 用户邮箱
     */
    User getUserByNameOrEmail(String userId, String userEmail);

    /**
     * 跟新最新登录时间
     *
     * @param userId            用户id
     * @param currentTimeMillis 最新登录时间
     */
    void updateLastLoginTime(int userId, long currentTimeMillis);

    /**
     * 根据用户或邮箱查询用户信息
     *
     * @param userName 用户名称
     * @param email    邮箱
     */
    User selectOneByUserNameOrEmail(String userName, String email);

    /**
     * 新增一个用户
     *
     * @param user 用户对象
     */
    void addOne(User user);

    /**
     * 根据用户和邮箱查询用户信息
     *
     * @param userName 用户名称
     * @param email    邮箱
     */
    User selectOneByUserNameAndEmail(String userName, String email);

    /**
     * 根据邮箱重制密码
     *
     * @param email       邮箱
     * @param newPassword 新密码
     */
    void updatePassword(String email, String newPassword);
}
