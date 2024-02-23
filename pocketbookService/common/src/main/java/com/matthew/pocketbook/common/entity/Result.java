package com.matthew.pocketbook.common.entity;

import com.matthew.pocketbook.common.constant.CommonConstant;
import com.matthew.pocketbook.common.util.UserContextHolder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("返回通用类")
public class Result<T> {
    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private int code;
    /**
     * 提示
     */
    @ApiModelProperty("提示")
    private String message;
    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;
    /**
     * 请求唯一id
     */
    @ApiModelProperty("请求唯一id")
    private String requestId;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.requestId = UserContextHolder.get().getRequestId();
    }

    public static Result unAuth() {
        return new Result(CommonConstant.UNAUTHORIZED_CODE, "用户未登录", null);
    }

    public static Result success(Object data) {
        return new Result(CommonConstant.SUCCESS_CODE, "success", data);
    }

    public static Result failed(String message) {
        return new Result(CommonConstant.ERROR_CODE, message, null);
    }

}
