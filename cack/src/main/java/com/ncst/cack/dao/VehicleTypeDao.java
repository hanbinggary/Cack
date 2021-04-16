package com.ncst.cack.dao;

import com.ncst.cack.model.VehicleType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Repository
public interface VehicleTypeDao {

    List<VehicleType> listVehicleType();

    int countName(String name);

    void insert(VehicleType vehicleType);

    void delete(Long id);
}
