package com.matthew.pocketbook.business.user.service.impl;

import com.matthew.pocketbook.business.user.entity.LoginParam;
import com.matthew.pocketbook.business.user.entity.LoginRes;
import com.matthew.pocketbook.business.user.entity.RegisterParam;
import com.matthew.pocketbook.business.user.entity.User;
import com.matthew.pocketbook.business.user.service.LoginService;
import com.matthew.pocketbook.business.user.service.UserService;
import com.matthew.pocketbook.common.constant.CommonConstant;
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
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Override
    public LoginRes login(LoginParam param) {
        User user = userService.getUserByNameOrEmail(param.getLoginCode(), param.getLoginCode());
        // 先md5加密后再sha1加密，降低sha1加密时的哈希碰撞
        if (user == null
            || !HashUtil.sha1(HashUtil.md5(param.getPassword())).equals(user.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        // 根据用户id生成token
        Map<String, Integer> map = new HashMap<>(1);
        map.put(CommonConstant.MDC_USER_ID, user.getUserId());
        String token = JwtUtil.encode(map, CommonConstant.SECRET, param.isRememberMe() ? CommonConstant.LONG_EXPIRE_TIME : CommonConstant.SHORT_EXPIRE_TIME);

        userService.updateLastLoginTime(user.getUserId(), System.currentTimeMillis());
        return LoginRes.builder()
            .token(token)
            .userId(user.getUserId())
            .email(user.getEmail())
            .userName(user.getUserName())
            .build();
    }

    @Override
    public void register(RegisterParam param) {
        User user = userService.selectOneByUserNameOrEmail(param.getUserName(), param.getUserName());
        if (user != null) {
            throw new CustomException("用户名已经注册");
        }
        user = userService.selectOneByUserNameOrEmail(param.getEmail(), param.getEmail());
        if (user != null) {
            throw new CustomException("邮箱已注册");
        }
        user = User.builder()
            .userName(param.getUserName())
            .email(param.getEmail())
            .password(HashUtil.sha1(HashUtil.md5(param.getPassword())))
            .createdTime(System.currentTimeMillis())
            .build();
        userService.addOne(user);
    }

    @Override
    public void resetPassword(RegisterParam param) {
        User user = userService.selectOneByUserNameAndEmail(param.getUserName(), param.getEmail());
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
        userService.updatePassword(param.getEmail(), newPassword);
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
        if (CommonConstant.IS_DEV) {
            authCode = "123456";
        } else {
            MailUtil.sendTextMail(mailInfo);
        }
        RedisUtil.set(getResetAuthCodeKey(email), authCode, CommonConstant.RESET_PASSWORD_EXPIRE_TIME);
    }

}
