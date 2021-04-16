package com.ncst.cack.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/04
 */
@Data
@Accessors(chain = true)
public class StatisticsDateDTO {
    private LocalDate date;
    private LocalDate total;
}
