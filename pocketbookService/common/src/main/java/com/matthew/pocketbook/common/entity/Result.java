package com.matthew.pocketbook.common.entity;

import com.matthew.pocketbook.common.constant.Constant;
import com.matthew.pocketbook.common.util.UserContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 后端返回包装类
 *
 * @author Matthew
 * @date 2021-01-28 17:15
 **/
@Data
@AllArgsConstructor
public class Result {
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
    /**
     * 请求唯一id
     */
    private String requestId;

    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.requestId = UserContextHolder.get().getRequestId();
    }

    public static Result unAuth() {
        return new Result(Constant.UNAUTHORIZED_CODE, "用户未登录", null);
    }

    public static Result success(Object data) {
        return new Result(Constant.SUCCESS_CODE, "success", data);
    }

    public static Result failed(String message) {
        return new Result(Constant.ERROR_CODE, message, null);
    }

}
