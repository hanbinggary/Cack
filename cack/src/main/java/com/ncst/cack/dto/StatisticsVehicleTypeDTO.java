package com.ncst.cack.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/04
 */
@Data
public class StatisticsVehicleTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String vehicleType;
    private Long total;
}
