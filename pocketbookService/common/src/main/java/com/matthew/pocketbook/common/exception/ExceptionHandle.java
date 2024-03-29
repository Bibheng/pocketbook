package com.matthew.pocketbook.common.exception;

import com.matthew.pocketbook.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常统一处理类
 *
 * @author Matthew
 * @date 2021-02-24 11:25
 **/
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        CustomException ce;
        if (e instanceof CustomException) {
            log.error("捕获到自定义错误{}", e.getMessage(), e);
            ce = (CustomException) e;
        } else if (e instanceof MethodArgumentNotValidException) {
            log.error("捕获到接口参数校验错误{}", e.getMessage(), e);
            MethodArgumentNotValidException be = (MethodArgumentNotValidException) e;
            FieldError fieldError = be.getBindingResult().getFieldError();
            ce = new CustomException(fieldError == null ? "接口参数校验异常" : fieldError.getDefaultMessage(), e);
        }  else {
            log.error("捕获到意外异常{}", e.getMessage(), e);
            ce = new CustomException("服务器异常");
        }
        return Result.failed(ce.getMessage());
    }
}
