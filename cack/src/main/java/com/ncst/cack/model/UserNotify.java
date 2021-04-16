package com.ncst.cack.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@Data
@Accessors(chain = true)
public class UserNotify implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private Long notifyId;
}
