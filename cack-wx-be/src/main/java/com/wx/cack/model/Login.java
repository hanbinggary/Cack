package com.wx.cack.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/12
 */
@Data
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    private String openid;
    private String unionid;
    private String session_key;
    private Integer errcode;
    private String errmsg;
}
