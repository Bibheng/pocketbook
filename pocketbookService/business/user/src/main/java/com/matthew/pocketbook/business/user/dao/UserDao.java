package com.matthew.pocketbook.business.user.dao;

import com.matthew.pocketbook.business.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 用户管理Dao
 *
 * @author Matthew
 * @date 2021-01-28 22:46
 **/
@Repository
public interface UserDao {
    /**
     * 根据用户名或邮箱查询用户信息
     *
     * @param userName 用户名
     * @param email 邮箱
     * @return com.matthew.pocketbook.business.user.entity.User
     * @author Matthew
     * @date 2021-01-28 23:03
     */
    User selectOneByUserNameOrEmail(@Param("userName") String userName, @Param("email") String email);

    /**
     * 根据用户id更新最新登录时间
     *
     * @param userId 用户id
     * @param time 登录时间
     * @author Matthew
     * @date 2021-01-29 11:14
     */
    @Update("update user set last_login_time = #{time} where user_id = #{userId}")
    void updateLastLoginTime(@Param("userId") int userId, @Param("time") long time);

    /**
     * 插入一条用户数据
     *
     * @param user 待插入用户信息
     * @author Matthew
     * @date 2021-02-03 14:49
     */
    void addOne(@Param("user") User user);

    /**
     * 更新密码
     *
     * @param email 用户邮箱
     * @param newPassword 新密码
     * @author Matthew
     * @date 2021-02-04 10:10
     */
    @Update("update user set password = #{newPassword} where email = #{email}")
    void updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);
}
