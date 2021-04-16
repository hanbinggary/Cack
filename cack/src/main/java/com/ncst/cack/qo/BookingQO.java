package com.ncst.cack.qo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/12
 */
@Data
public class BookingQO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String searchText;
    private int orderByGmtCreate;
    private String filterByOrderStatus;
    private List<Integer> filterByOrderStatusList;
    private int pageNum;
    private int pageSize;
}
