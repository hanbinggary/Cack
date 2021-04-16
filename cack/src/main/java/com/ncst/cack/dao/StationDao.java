package com.ncst.cack.dao;

import com.ncst.cack.dto.StationDTO;
import com.ncst.cack.model.Station;
import org.springframework.stereotype.Repository;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Repository
public interface StationDao {
    StationDTO one();

    void update(Station station);
}
