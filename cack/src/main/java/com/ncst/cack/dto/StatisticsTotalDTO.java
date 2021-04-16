package com.ncst.cack.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/04
 */
@Data
@Accessors(chain = true)
public class StatisticsTotalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long bookingTotal;
    private Long vehicleTotal;
    private Long userTotal;
}
