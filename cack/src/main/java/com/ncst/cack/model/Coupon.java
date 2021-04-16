package com.ncst.cack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/08
 */
@Data
public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int money;
    private LocalDateTime expiredTime;

    @FutureOrPresent(message = "日期必须是现在或未来日期")
    private LocalDate date;

    @Range(min = 0, max = 23, message = "小时必须在0到23之间")
    private int hours;

    @Range(min = 0, max = 59, message = "分钟必须在0到59之间")
    private int minutes;

    @Range(min = 0, max = 59, message = "秒钟必须在0到59之间")
    private int seconds;
}
