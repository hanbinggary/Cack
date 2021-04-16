package com.wx.cack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 获得预约时间段
 * @author liuxuelian
 * @date 2021/04/14
 */
@Data
public class BookingTime implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int startTime;
    private int endTime;
    private Boolean status;
    private int total;
    private String invalidReason;
}
