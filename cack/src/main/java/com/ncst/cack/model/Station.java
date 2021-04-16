package com.ncst.cack.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
public class Station implements Serializable {
    private static final long serialVersionUID = 1L;

    @Length(max = 20, message = "名称必须小于20个字符")
    private String name;

    @Length(max = 50, message = "地址必须小于50个字符")
    private String addr;

    @Length(max = 10, message = "经度必须小于10个字符")
    private String longitude;

    @Length(max = 10, message = "纬度必须小于10个字符")
    private String latitude;

    @Length(max = 200, message = "描述必须小于200个字符")
    private String desc;

    @Length(max = 11, message = "手机号必须是11位数字")
    private String phone;

    @Length(max = 15, message = "电话号必须小于15个字符")
    private String tel;

    @Length(min = 11, max = 11, message = "营业时间必须是11个字符")
    private String openingTime;

    @Range(min = 1, message = "时段最大预约数必须大于0")
    private Integer maxTimeBookingTotal;
}
