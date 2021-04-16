package com.ncst.cack.qo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/07
 */
@Data
public class BookingTimeDeleteBatchQO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Long> ids;
    private String invalidReason;
}
