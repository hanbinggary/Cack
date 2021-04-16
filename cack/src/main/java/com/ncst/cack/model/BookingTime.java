package com.ncst.cack.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
@Accessors(chain = true)
public class BookingTime implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate date;
    private int startTime;
    private int endTime;
}
