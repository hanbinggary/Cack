package com.ncst.cack.util;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * unified response model
 * @author liuxuelian
 * @date 2021/03/23
 */
@Data
@Accessors(chain = true)
public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T result;

    /**
     * handle success response
     * @param code
     * @param msg
     * @param list
     */
    public static <T> ResponseModel OK(int code, String msg, T list) {
        return new ResponseModel()
                .setCode(code)
                .setMessage(msg)
                .setResult(list);
    }

    /**
     * handle success response
     * @param httpStatus
     */
    public static <T> ResponseModel OK(HttpStatus httpStatus) {
        return OK(httpStatus.value(), httpStatus.getReasonPhrase(), null);
    }

    /**
     * handle success response
     * @param list response data list
     */
    public static <T> ResponseModel OK(T list) {
        return OK(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list);
    }

    /**
     * handle success response
     */
    public static <T> ResponseModel OK() {
        return OK(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    /**
     * handle failed response
     */
    public static <T> ResponseModel FAILED(int code, String msg, T list) {
        return new ResponseModel()
                .setCode(code)
                .setMessage(msg)
                .setResult(list);
    }

    /**
     * handle failed response
     * @param httpStatus
     */
    public static <T> ResponseModel FAILED(HttpStatus httpStatus) {
        return FAILED(httpStatus.value(), httpStatus.getReasonPhrase(), null);
    }

    /**
     * handle failed response
     */
    public static <T> ResponseModel FAILED() {
        return FAILED(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handle 400 status bad request response
     */
    public static <T> ResponseModel BAD_REQUEST() {
        return FAILED(HttpStatus.BAD_REQUEST);
    }

    /**
     * handle 400 status bad request response
     * @param msg
     */
    public static <T> ResponseModel BAD_REQUEST(String msg) {
        return new ResponseModel().setCode(400).setMessage(msg);
    }

    /**
     * handle 401 status unauthorized response
     */
    public static <T> ResponseModel UNAUTHORIZED() {
        return FAILED(HttpStatus.UNAUTHORIZED);
    }

    /**
     * handle 403 status forbidden response
     */
    public static <T> ResponseModel FORBIDDEN() {
        return FAILED(HttpStatus.FORBIDDEN);
    }

    /**
     * handle 423 status locked response
     */
    public static <T> ResponseModel LOCKED() {
        return FAILED(HttpStatus.LOCKED);
    }
}
