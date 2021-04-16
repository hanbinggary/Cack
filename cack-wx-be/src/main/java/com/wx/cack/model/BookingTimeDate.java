package com.wx.cack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Data
public class BookingTimeDate implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate date;
    private int timeCount;
    private int totals;
}
