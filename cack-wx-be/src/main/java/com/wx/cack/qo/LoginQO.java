package com.wx.cack.qo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/12
 */
@Data
public class LoginQO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "code不能为空")
    private String code;
    private String avatarUrl;
    private String country;
    private String province;
    private String city;
    private int gender;
    private String nickName;
}
