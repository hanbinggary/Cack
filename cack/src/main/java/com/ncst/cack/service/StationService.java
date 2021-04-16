package com.ncst.cack.service;

import com.ncst.cack.dao.StationDao;
import com.ncst.cack.dto.StationDTO;
import com.ncst.cack.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Service
public class StationService {
    @Autowired
    StationDao stationDao;

    public StationDTO one() {
        return stationDao.one();
    }

    public void update(Station station) {
        if (station.getName() != null || station.getAddr() != null
            || station.getLongitude() != null || station.getLatitude() != null
            || station.getDesc() != null || station.getPhone() != null
            || station.getTel() != null || station.getOpeningTime() != null
            || station.getMaxTimeBookingTotal() != null) {
            stationDao.update(station);
        }
    }
}
