package com.matthew.pocketbook.business.user.service.impl;

import com.matthew.pocketbook.business.user.dao.UserDao;
import com.matthew.pocketbook.business.user.entity.LoginParam;
import com.matthew.pocketbook.business.user.entity.LoginRes;
import com.matthew.pocketbook.business.user.entity.RegisterParam;
import com.matthew.pocketbook.business.user.entity.User;
import com.matthew.pocketbook.business.user.service.UserService;
import com.matthew.pocketbook.common.constant.Constant;
import com.matthew.pocketbook.common.entity.MailInfo;
import com.matthew.pocketbook.common.exception.CustomException;
import com.matthew.pocketbook.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理接口
 *
 * @author Matthew
 * @date 2021-01-28 22:43
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public LoginRes login(LoginParam param) {
        User user = userDao.selectOneByUserNameOrEmail(param.getLoginCode(), param.getLoginCode());
        // 先md5加密后再sha1加密，降低sha1加密是的哈希碰撞
        if (user == null
            || !HashUtil.sha1(HashUtil.md5(param.getPassword())).equals(user.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        Map<String, Integer> map = new HashMap<>(1);
        map.put("userId", user.getUserId());
        String token = JwtUtil.encode(map, Constant.SECRET, param.isRememberMe() ? Constant.LONG_EXPIRE_TIME : Constant.SHORT_EXPIRE_TIME);
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
        if (user != null) {
            if (user.getUserName().equals(param.getUserName())) {
                throw new CustomException("用户名已经注册");
            }
            if (user.getEmail().equals(param.getEmail())) {
                throw new CustomException("邮箱已注册");
            }
        }
        user = User.builder()
            .userName(param.getUserName())
            .email(param.getEmail())
            .password(HashUtil.sha1(HashUtil.md5(param.getPassword())))
            .createdTime(System.currentTimeMillis())
            .build();
        userDao.addOne(user);
    }

    @Override
    public void resetPassword(RegisterParam param) {
        User user = userDao.selectOneByUserNameOrEmail(param.getUserName(), param.getEmail());
        if (user == null) {
            throw new CustomException("用户不存在");
        }
        String redisKey = getResetAuthCodeKey(param.getEmail());
        String authCode = RedisUtil.get(redisKey, String.class);
        if (StringUtil.isEmpty(authCode) || !authCode.equals(param.getAuthCode())) {
            throw new CustomException("验证码错误");
        }
        RedisUtil.delete(redisKey);
        String newPassword = HashUtil.sha1(HashUtil.md5(param.getPassword()));
        userDao.updatePassword(param.getEmail(), newPassword);
    }

    /**
     * 生成重置密码缓存key
     *
     * @param email 用户邮箱
     * @return java.lang.String
     * @author Matthew
     * @date 2021-02-22 16:23
     */
    private String getResetAuthCodeKey(String email) {
        return email + "_authCode";
    }


    @Override
    public void sendAuthCode(String email) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setReceiver(email);
        mailInfo.setSubject("记账本 验证码");
        String authCode = StringUtil.getRandomString(6, 2);
        mailInfo.setContent("记账本，本次验证码：" + authCode);
        if (Constant.isDev) {
            authCode = "123456";
        } else {
            MailUtil.sendTextMail(mailInfo);
        }
        RedisUtil.set(getResetAuthCodeKey(email), authCode, Constant.RESET_PASSWORD_EXPIRE_TIME);
    }

}
