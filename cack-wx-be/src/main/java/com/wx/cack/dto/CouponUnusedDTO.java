package com.wx.cack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/14
 */
@Data
public class CouponUnusedDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean used;
    private int money;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredTime;
}
