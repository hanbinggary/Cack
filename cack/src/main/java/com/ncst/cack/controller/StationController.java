package com.ncst.cack.controller;

import com.ncst.cack.model.Station;
import com.ncst.cack.service.StationService;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    StationService stationService;

    @GetMapping
    public ResponseModel stationOne() {
        return ResponseModel.OK(stationService.one());
    }

    @PutMapping
    public ResponseModel stationUpdate(@Valid @RequestBody Station station) {
        System.out.println("station = " + station);
        stationService.update(station);
        return ResponseModel.OK(1);
    }
}
