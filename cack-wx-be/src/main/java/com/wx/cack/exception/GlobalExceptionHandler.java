package com.wx.cack.exception;

import com.wx.cack.util.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局统一异常处理
 * @author liuxuelian
 * @date 2021/04/02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseModel methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            logger.error("methodArgumentNotValidException ==> {}",  bindingResult.getFieldError().getDefaultMessage());
            return ResponseModel.BAD_REQUEST(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResponseModel.FAILED();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseModel constraintViolationException(ConstraintViolationException e) {
        String message = e.getMessage();
        String msg = message.substring(message.lastIndexOf(":"));
        return ResponseModel.BAD_REQUEST(msg);
    }


    @ExceptionHandler(Exception.class)
    public ResponseModel exception(Exception e) {
        logger.error("exception ==> {}",  e.getMessage());
        return ResponseModel.FAILED();
    }
}
