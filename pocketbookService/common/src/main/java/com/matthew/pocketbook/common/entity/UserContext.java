package com.matthew.pocketbook.common.entity;

import lombok.Data;

/**
 * 用户信息上下文
 *
 * @author Matthew
 * @date 2021-02-25 17:14
 **/
@Data
public class UserContext {
    private int userId;
    private String requestId;
}
