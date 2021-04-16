package com.ncst.cack.service;

import com.ncst.cack.dao.VehicleTypeDao;
import com.ncst.cack.model.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Service
public class VehicleTypeService {
    @Autowired
    VehicleTypeDao vehicleTypeDao;

    public List<VehicleType> listVehicleType() {
        return vehicleTypeDao.listVehicleType();
    }

    public Long insert(VehicleType vehicleType) {
        int countName = vehicleTypeDao.countName(vehicleType.getName());
        if (countName > 0) {
            return -1L;
        }
        vehicleTypeDao.insert(vehicleType);
        return vehicleType.getId();
    }

    public Long delete(Long id) {
        vehicleTypeDao.delete(id);
        return id;
    }
}
