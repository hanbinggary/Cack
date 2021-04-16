package com.wx.cack.dao;

import com.wx.cack.dto.VehicleDTO;
import com.wx.cack.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Repository
public interface VehicleDao {

    List<VehicleDTO> listVehicle(Long userId);

    List<VehicleDTO> canBookingListVehicle(Long userId);

    VehicleDTO one(Long id);

    int countVehicleByUserId(Long userId);

    int countVehicleByPlateNo(Long userId, String plateNo);

    void insert(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(Long id);
}
