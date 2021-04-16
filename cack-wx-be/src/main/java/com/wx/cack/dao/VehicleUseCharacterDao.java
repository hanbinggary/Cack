package com.wx.cack.dao;

import com.wx.cack.dto.VehicleUseCharacterDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Repository
public interface VehicleUseCharacterDao {
    List<VehicleUseCharacterDTO> listVehicleUseCharacter();
}
