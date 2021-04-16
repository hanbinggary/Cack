package com.ncst.cack.controller;

import com.ncst.cack.model.VehicleType;
import com.ncst.cack.model.VehicleUseCharacter;
import com.ncst.cack.service.VehicleTypeService;
import com.ncst.cack.service.VehicleUseCharacterService;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@RestController
@RequestMapping("/vehicle/use/character")
public class VehicleUseCharacterController {
    @Autowired
    VehicleUseCharacterService vehicleUseCharacterService;

    @GetMapping
    public ResponseModel listVehicleUseCharacter() {
        return ResponseModel.OK(vehicleUseCharacterService.listVehicleUseCharacter());
    }

    @PostMapping
    public ResponseModel vehicleUseCharacterInsert(@Valid @RequestBody VehicleUseCharacter vehicleUseCharacter) {
        Long index = vehicleUseCharacterService.insert(vehicleUseCharacter);
        if (index.equals(-1L)) {
            return ResponseModel.BAD_REQUEST("使用性质名称已经存在");
        }
        return ResponseModel.OK(index);
    }

    @DeleteMapping("/{id}")
    public ResponseModel vehicleUseCharacterDelete(@PathVariable Long id) {
        return ResponseModel.OK(vehicleUseCharacterService.delete(id));
    }
}
