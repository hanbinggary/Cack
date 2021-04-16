package com.ncst.cack.qo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/08
 */
@Data
public class CouponQO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int expiredSign; // 0-过期，1-未过期，2-默认
    private int orderByExpiredTime; // 0-默认，1-升序，2-降序
    private int pageNum;
    private int pageSize;
}
