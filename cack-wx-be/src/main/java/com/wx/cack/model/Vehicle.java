package com.wx.cack.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Data
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "车牌号不能为空")
    @Length(max = 10, message = "车牌号必须小于10个字符")
    private String plateNo;

    @Length(max = 10, message = "车辆类型必须小于10个字符")
    private String vehicleType;

    @Length(max = 10, message = "使用性质必须小于10个字符")
    private String useCharacter;

    private int odometer;

    @NotNull(message = "车辆识别代码不能为空")
    @Length(max = 17, message = "车辆识别代码必须小于17个字符")
    private String vin;

    @NotNull(message = "发动机代码不能为空")
    @Length(max = 15, message = "发动机代码必须小于15个字符")
    private String engineNo;

    @PastOrPresent(message = "车辆注册日期必须是过去或现在日期")
    private LocalDate registerDate;

    @NotNull(message = "用户ID不能为空")
    private Long userId;

}
