package com.ncst.cack.qo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
public class AdminUpdateQO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Length(min = 3, max = 20, message = "昵称必须在3到20个字符之间")
    private String nickname;

    @Length(max = 11, message = "手机号必须是11位数字")
    private String phone;
}
