package com.ncst.cack.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/04
 */
@Data
public class StatisticsGenderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int type;
    private Long total;
}
