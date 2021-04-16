package com.wx.cack.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/12
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String openid;
    private String sessionKey;
    private String nickname;
    private String avatarUrl;
    private String tel;
    private int gender;
    private String country;
    private String province;
    private String city;
    private Boolean status;
}
