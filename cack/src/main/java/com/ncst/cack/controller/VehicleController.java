package com.ncst.cack.controller;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.VehicleDao;
import com.ncst.cack.dto.VehicleDTO;
import com.ncst.cack.service.VehicleService;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/07
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping
    public ResponseModel vehicleList(@RequestParam(name = "searchValue", defaultValue = "") String searchValue,
                                     @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        List<VehicleDTO> vehicles = vehicleService.listVehicle(searchValue, pageNum, pageSize);
        return ResponseModel.OK(Paging.setPaging(vehicles));
    }

    @GetMapping("/{id}")
    public ResponseModel vehicleOne(@PathVariable Long id) {
        return ResponseModel.OK(vehicleService.getVehicleById(id));
    }
}
