package com.wx.cack.service;

import com.wx.cack.dao.StationDao;
import com.wx.cack.dto.StationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Service
public class StationService {
    @Autowired
    StationDao stationDao;

    public StationDTO getStation() {
        return stationDao.one();
    }
}
