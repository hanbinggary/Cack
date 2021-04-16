package com.wx.cack.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Data
@Accessors(chain = true)
public class Notify implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String content;
}
