package com.ncst.cack.qo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
public class UserQO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nickname;
    private int statusSign; // 0-false, 1-true, 2-默认
    private LocalDate date;
    private int orderByLastLoginTime; // 0-默认, 1-升序, 2-降序
    private int pageNum;
    private int pageSize;
}
