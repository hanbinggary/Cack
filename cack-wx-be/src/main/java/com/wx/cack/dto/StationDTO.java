package com.wx.cack.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Data
public class StationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String addr;
    private String longitude;
    private String latitude;
    private String desc;
    private String phone;
    private String tel;
    private String openingTime;

}
