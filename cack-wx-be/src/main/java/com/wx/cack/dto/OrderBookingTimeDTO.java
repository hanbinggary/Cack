package com.wx.cack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Data
public class OrderBookingTimeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate date;
    private int startTime;
    private int endTime;
    private Boolean status;
    private Long total;
    private String invalidReason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
}
