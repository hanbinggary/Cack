package com.wx.cack.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Data
public class VehicleUseCharacterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
}