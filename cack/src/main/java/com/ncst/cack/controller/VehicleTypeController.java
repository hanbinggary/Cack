package com.ncst.cack.controller;

import com.ncst.cack.model.VehicleType;
import com.ncst.cack.service.VehicleTypeService;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@RestController
@RequestMapping("/vehicle/type")
public class VehicleTypeController {
    @Autowired
    VehicleTypeService vehicleTypeService;

    @GetMapping
    public ResponseModel listVehicleType() {
        return ResponseModel.OK(vehicleTypeService.listVehicleType());
    }

    @PostMapping
    public ResponseModel vehicleTypeInsert(@Valid @RequestBody VehicleType vehicleType) {
        Long index = vehicleTypeService.insert(vehicleType);
        if (index.equals(-1L)) {
            return ResponseModel.BAD_REQUEST("类型名称已经存在");
        }
        return ResponseModel.OK(index);
    }

    @DeleteMapping("/{id}")
    public ResponseModel vehicleTypeDelete(@PathVariable Long id) {
        return ResponseModel.OK(vehicleTypeService.delete(id));
    }
}
