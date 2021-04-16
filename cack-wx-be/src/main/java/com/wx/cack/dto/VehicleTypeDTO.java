package com.wx.cack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Data
public class VehicleTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
}