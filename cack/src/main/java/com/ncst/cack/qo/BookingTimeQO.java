package com.ncst.cack.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
public class BookingTimeQO implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate date;
    private int orderByTotal; // 0-默认，1-升序，2-降序
    private int statusSign; // 0-false, 1-true, 2-默认
    private int pageNum;
    private int pageSize;
}
