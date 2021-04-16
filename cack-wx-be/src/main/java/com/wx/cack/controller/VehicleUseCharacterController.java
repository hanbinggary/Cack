package com.wx.cack.controller;

import com.wx.cack.service.VehicleTypeService;
import com.wx.cack.service.VehicleUseCharacterService;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@RestController
@RequestMapping("/vehicle/use/character")
public class VehicleUseCharacterController {
    @Autowired
    VehicleUseCharacterService vehicleUseCharacterService;

    @GetMapping
    public ResponseModel vehicleTypeList() {
        return ResponseModel.OK(vehicleUseCharacterService.listVehicleUseCharacter());
    }
}
