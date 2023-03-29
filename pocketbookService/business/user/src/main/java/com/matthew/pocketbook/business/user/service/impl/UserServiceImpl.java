package com.matthew.pocketbook.business.user.service.impl;

import com.matthew.pocketbook.business.user.mapper.UserMapper;
import com.matthew.pocketbook.business.user.entity.User;
import com.matthew.pocketbook.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户通用服务实现类
 *
 * @author songzeheng
 * @date 2022/10/12 23:40
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByNameOrEmail(String userId, String userEmail) {
        return userMapper.selectOneByUserNameOrEmail(userId, userEmail);
    }

    @Override
    public void updateLastLoginTime(int userId, long currentTimeMillis) {
        userMapper.updateLastLoginTime(userId, currentTimeMillis);
    }

    @Override
    public User selectOneByUserNameOrEmail(String userName, String email) {
        return userMapper.selectOneByUserNameOrEmail(userName, email);
    }

    @Override
    public void addOne(User user) {
        userMapper.addOne(user);
    }

    @Override
    public User selectOneByUserNameAndEmail(String userName, String email) {
        return userMapper.selectOneByUserNameAndEmail(userName, email);
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        userMapper.updatePassword(email, newPassword);
    }
}
