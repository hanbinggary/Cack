package com.wx.cack.dao;

import com.wx.cack.dto.StationDTO;
import org.springframework.stereotype.Repository;


/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Repository
public interface StationDao {
    StationDTO one();

    int queryMaxTimeBookingTotal();
}
