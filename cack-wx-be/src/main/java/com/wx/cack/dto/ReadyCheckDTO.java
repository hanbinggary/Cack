package com.wx.cack.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 准备车检或者车检中列表
 * @author liuxuelian
 * @date 2021/04/13
 */
@Data
public class ReadyCheckDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String orderNo;
    private int status;
    private String plateNo;
    private String vehicleType;
    private String useCharacter;
    private String engineNo;
    private String vin;
    private LocalDate registerDate;
    private int odometer;
}
