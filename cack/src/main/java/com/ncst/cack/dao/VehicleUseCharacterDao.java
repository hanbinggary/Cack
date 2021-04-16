package com.ncst.cack.dao;

import com.ncst.cack.model.VehicleType;
import com.ncst.cack.model.VehicleUseCharacter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Repository
public interface VehicleUseCharacterDao {

    List<VehicleUseCharacter> listVehicleUseCharacter();

    int countUseCharacterName(String name);

    void insert(VehicleUseCharacter vehicleUseCharacter);

    void delete(Long id);
}
