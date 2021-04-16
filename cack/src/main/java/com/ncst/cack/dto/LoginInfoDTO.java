package com.ncst.cack.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
@Accessors(chain = true)
public class LoginInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    protected String nickname;
    private int passwordLevel;
    private String roleName;
    private String token;
}
