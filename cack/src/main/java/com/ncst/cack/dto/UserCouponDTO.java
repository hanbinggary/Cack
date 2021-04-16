package com.ncst.cack.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户领取优惠券表
 * @author liuxuelian
 * @date 2021/04/10
 */
@Data
public class UserCouponDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean used;
    private int money;
    private Boolean expired;
    private Boolean status;
}
