package com.wx.cack.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Data
public class BookingCountStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int readyCheckTotal;
    private int checkingTotal;
}
