package com.matthew.pocketbook.common.exception;

/**
 * 用户自定义异常
 *
 * @author Matthew
 * @date 2021-01-28 22:14
 **/
public class CustomException extends RuntimeException{
    public CustomException() {
        super();
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
