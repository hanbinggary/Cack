package com.wx.cack.service;

import com.wx.cack.dao.VehicleTypeDao;
import com.wx.cack.dao.VehicleUseCharacterDao;
import com.wx.cack.dto.VehicleTypeDTO;
import com.wx.cack.dto.VehicleUseCharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Service
public class VehicleUseCharacterService {
    @Autowired
    VehicleUseCharacterDao vehicleUseCharacterDao;

    public List<VehicleUseCharacterDTO> listVehicleUseCharacter() {
        return vehicleUseCharacterDao.listVehicleUseCharacter();
    }
}
