package com.wx.cack.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 预约下单
 * @author liuxuelian
 * @date 2021/04/14
 */
@Data
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String orderNo;

    private Long couponId;

    @NotNull(message = "预约时间ID不能为空")
    private Long timeId;

    @NotNull(message = "车辆ID不能为空")
    private Long vehicleId;

    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
