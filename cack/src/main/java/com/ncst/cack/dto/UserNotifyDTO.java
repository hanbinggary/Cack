package com.ncst.cack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@Data
public class UserNotifyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean readed;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime readedTime;

    private Long userId;
    private String nickname;

}
