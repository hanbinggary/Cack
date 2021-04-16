package com.wx.cack.controller;

import com.wx.cack.service.StationService;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    StationService stationService;

    @GetMapping
    public ResponseModel stationOne() {
        return ResponseModel.OK(stationService.getStation());
    }
}
