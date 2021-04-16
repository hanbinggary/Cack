package com.ncst.cack.service;

import com.ncst.cack.dao.VehicleTypeDao;
import com.ncst.cack.dao.VehicleUseCharacterDao;
import com.ncst.cack.model.VehicleType;
import com.ncst.cack.model.VehicleUseCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Service
public class VehicleUseCharacterService {
    @Autowired
    VehicleUseCharacterDao vehicleUseCharacterDao;

    public List<VehicleUseCharacter> listVehicleUseCharacter() {
        return vehicleUseCharacterDao.listVehicleUseCharacter();
    }

    public Long insert(VehicleUseCharacter vehicleUseCharacter) {
        int countUseCharacterName = vehicleUseCharacterDao.countUseCharacterName(vehicleUseCharacter.getName());
        if (countUseCharacterName > 0) {
            return -1L;
        }
        vehicleUseCharacterDao.insert(vehicleUseCharacter);
        return vehicleUseCharacter.getId();
    }

    public Long delete(Long id) {
        vehicleUseCharacterDao.delete(id);
        return id;
    }
}
