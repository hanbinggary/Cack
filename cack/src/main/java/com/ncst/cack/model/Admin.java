package com.ncst.cack.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
@Accessors(chain = true)
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名必须在3到20个字符之间")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码必须在6到20个字符之间")
    private String password;

    private int passwordLevel;

    @NotNull(message = "昵称不能为空")
    @Length(min = 3, max = 20, message = "昵称必须在3到20个字符之间")
    private String nickname;

    @Length(max = 11, message = "手机号必须是11位数字")
    private String phone;

    @NotNull(message = "角色不能为空")
    private Long roleId;
}
