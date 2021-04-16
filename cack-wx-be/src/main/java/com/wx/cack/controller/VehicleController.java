package com.wx.cack.controller;

import com.wx.cack.dao.BookingDao;
import com.wx.cack.dao.VehicleDao;
import com.wx.cack.model.Vehicle;
import com.wx.cack.service.VehicleService;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @Autowired
    BookingDao bookingDao;

    @GetMapping("/all/{userId}")
    public ResponseModel vehicleList(@PathVariable Long userId) {
        return ResponseModel.OK(vehicleService.listVehicle(userId));
    }

    @GetMapping("/can/booking/{userId}")
    public ResponseModel vehicleCanBookingList(@PathVariable Long userId) {
        return ResponseModel.OK(vehicleService.canBookingListVehicle(userId));
    }

    @GetMapping("/{id}")
    public ResponseModel vehicleOne(@PathVariable Long id) {
        return ResponseModel.OK(vehicleService.getVehicleById(id));
    }

    @GetMapping("/count/{userId}")
    public ResponseModel vehicleCount(@PathVariable Long userId) {
        return ResponseModel.OK(vehicleService.countVehicleByUserId(userId));
    }

    @PostMapping
    public ResponseModel vehicleInsert(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.insert(vehicle);
    }

    @PutMapping
    public ResponseModel vehicleUpdate(@Valid @RequestBody Vehicle vehicle) {
        return ResponseModel.OK(vehicleService.update(vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseModel vehicleDelete(@PathVariable Long id) {
        return ResponseModel.OK(vehicleService.delete(id));
    }
}
