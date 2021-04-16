package com.ncst.cack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/10
 */
@Data
public class BookingVehicleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String plateNo;
    private String vehicleType;
    private String useCharacter;
    private String vin;
    private String engineNo;
    private int odometer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
}
