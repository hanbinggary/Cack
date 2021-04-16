package com.wx.cack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 根据日期分组
 * @author liuxuelian
 * @date 2021/04/13
 */
@Data
@Accessors(chain = true)
public class BookingTimeDateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int timeCount;
    private int totals;
    private String degreeOfCrowding; // 拥挤程度 "<=1/3"推荐 ">1/3 & <=2/3"拥挤 ">2/3"爆满
}