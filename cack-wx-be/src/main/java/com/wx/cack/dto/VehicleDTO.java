package com.wx.cack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Data
public class VehicleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String plateNo;
    private String vehicleType;
    private String useCharacter;
    private String vin;
    private String engineNo;
    private int odometer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime registerDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
}
