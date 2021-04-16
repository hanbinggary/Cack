package com.ncst.cack.controller;

import com.ncst.cack.service.StatisticsService;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxuelian
 * @date 2021/04/04
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    /**
     * 统计 总预约数、总车辆数、总用户数
     * @return
     */
    @GetMapping("/total")
    public ResponseModel statisticsTotal() {
        return ResponseModel.OK(statisticsService.total());
    }



    /**
     *  周 用户数、预约数
     */
    @GetMapping("/weekly")
    public ResponseModel statisticsWeekly() {
        return  ResponseModel.OK(statisticsService.countWeekly());
    }

    /**
     *  月 用户数、预约数
     */
    @GetMapping("/month")
    public ResponseModel statisticsMonth() {
        return  ResponseModel.OK(statisticsService.countMonth());
    }

    /**
     * 统计 用户性别
     */
    @GetMapping("/gender")
    public ResponseModel statisticsGender() {
        return  ResponseModel.OK(statisticsService.countGender());
    }

    /**
     * 统计 车辆类型
     */
    @GetMapping("/vehicle/type")
    public ResponseModel statisticsVehicleType() {
        return ResponseModel.OK(statisticsService.countVehicleType());
    }
}
