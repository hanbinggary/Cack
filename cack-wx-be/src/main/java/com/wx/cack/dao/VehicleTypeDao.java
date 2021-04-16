package com.wx.cack.dao;

import com.wx.cack.dto.VehicleTypeDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Repository
public interface VehicleTypeDao {
    List<VehicleTypeDTO> listVehicleType();
}
