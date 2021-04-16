package com.wx.cack.controller;

import com.wx.cack.dto.BookingTimeDateDTO;
import com.wx.cack.service.BookingTimeService;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@RestController
@RequestMapping("/booking/time")
public class BookingTimeController {
    @Autowired
    BookingTimeService bookingTimeService;

    @GetMapping("/date")
    public ResponseModel bookingTimeDateTotal() {
        return ResponseModel.OK(bookingTimeService.getDateTotal());
    }

    @GetMapping("/query/time/date")
    public ResponseModel bookingTimeTimeByDate(LocalDate date) {
        return ResponseModel.OK(bookingTimeService.queryTimeByDate(date));
    }
}
