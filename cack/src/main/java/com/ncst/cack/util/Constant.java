package com.ncst.cack.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举常量
 * @author liuxuelian
 * @date 2021/04/02
 */
@Getter
@AllArgsConstructor
public enum Constant {
    USERNAME_OR_PASSWORD_ERROR("用户名或密码错误", "Username or password error"),
    USER_DISABLED("该账户已被停用", "User is disabled");

    private String message;
    private String englishMessage;

}
