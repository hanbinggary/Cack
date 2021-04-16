package com.wx.cack.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/14
 */
@Data
@Accessors(chain = true)
public class BookingTimeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int startTime;
    private int endTime;
    private int total;
    private String degreeOfCrowding; // 拥挤程度 "<=1/3"推荐 ">1/3 & <=2/3"拥挤 ">2/3"爆满
}
