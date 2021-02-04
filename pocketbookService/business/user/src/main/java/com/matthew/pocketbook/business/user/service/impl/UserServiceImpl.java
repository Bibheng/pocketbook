package com.matthew.pocketbook.business.user.service.impl;

import com.matthew.pocketbook.business.user.dao.UserDao;
import com.matthew.pocketbook.business.user.entity.LoginParam;
import com.matthew.pocketbook.business.user.entity.LoginRes;
import com.matthew.pocketbook.business.user.entity.RegisterParam;
import com.matthew.pocketbook.business.user.entity.User;
import com.matthew.pocketbook.business.user.service.UserService;
import com.matthew.pocketbook.common.constant.Constant;
import com.matthew.pocketbook.common.exception.CustomException;
import com.matthew.pocketbook.common.util.HashUtil;
import com.matthew.pocketbook.common.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理接口
 *
 * @author Matthew
 * @date 2021-01-28 22:43
 **/

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public LoginRes login(LoginParam param) {
        User user = userDao.selectOneByUserNameOrEmail(param.getLoginCode(), param.getLoginCode());
        // 先md5加密后再sha1加密，降低sha1加密是的哈希碰撞
        if (user == null
            || !HashUtil.sha1(HashUtil.md5(param.getPassword())).equals(param.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        Map map = new HashMap<String, String>(1);
        map.put("userId", String.valueOf(user.getUserId()));
        String token = JWTUtil.encode(map, Constant.SECRET, param.isRememberMe() ? Constant.LONG_EXPIRE_TIME : Constant.SHORT_EXPIRE_TIME);
        userDao.updateLastLoginTime(user.getUserId(), System.currentTimeMillis());
        return LoginRes.builder()
            .token(token)
            .userId(user.getUserId())
            .email(user.getEmail())
            .userName(user.getUserName())
            .build();
    }

    @Override
    public void register(RegisterParam param) {
        User user = userDao.selectOneByUserNameOrEmail(param.getUserName(), param.getEmail());
        if (user.getUserName().equals(param.getUserName())) {
            throw new CustomException("用户名已经注册");
        }
        if (user.getEmail().equals(param.getEmail())) {
            throw new CustomException("邮箱已注册");
        }
        User.builder()
            .userName(user.getUserName())
            .email(user.getEmail())
            .password(HashUtil.sha1(HashUtil.md5(user.getPassword())))
            .createTime(System.currentTimeMillis())
            .build();
        userDao.addOne(user);
    }

    @Override
    public void resetPassword(RegisterParam param) {
        User user = userDao.selectOneByUserNameOrEmail(param.getUserName(), param.getEmail());
        if (user == null) {
            throw new CustomException("用户不存在");
        }
        // todo:增加验证码校验
        String newPassword = HashUtil.sha1(HashUtil.md5(param.getPassword()));
        userDao.updatePassword(param.getEmail(), newPassword);
    }


}
