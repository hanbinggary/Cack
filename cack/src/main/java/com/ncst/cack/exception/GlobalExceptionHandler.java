package com.ncst.cack.exception;

import com.ncst.cack.util.ResponseModel;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

    /**
     * handle forbidden access exception {@code 403}
     * @param e
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseModel accessDeniedException(AccessDeniedException e) {
        logger.error("accessDeniedException: {}",  e.getMessage());
        return ResponseModel.FORBIDDEN();
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseModel expiredJwtException(ExpiredJwtException e) {
//        logger.error("expiredJwtException: {}",  e.getMessage());
//        return ResponseModel.UNAUTHORIZED();
//    }

    @ExceptionHandler(Exception.class)
    public ResponseModel exception(Exception e) {
        System.out.println("e.getClass() = " + e.getClass());
        logger.error("exception ==> {}",  e.getMessage());
        return ResponseModel.FAILED();
    }
}
