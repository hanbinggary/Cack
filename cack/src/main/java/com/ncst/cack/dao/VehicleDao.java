package com.ncst.cack.dao;

import com.ncst.cack.dto.VehicleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/07
 */
@Repository
public interface VehicleDao {
    List<VehicleDTO> listVehicle(String searchValue);

    VehicleDTO one(Long id);
}
