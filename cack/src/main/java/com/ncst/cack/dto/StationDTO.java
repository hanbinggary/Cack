package com.ncst.cack.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
public class StationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String addr;
    private String longitude;
    private String latitude;
    private String desc;
    private String phone;
    private String tel;
    private String openingTime;
    private String picture;
    private int maxTimeBookingTotal;
    private LocalDateTime gmtModified;
}
