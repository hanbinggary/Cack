package com.wx.cack.controller;

import com.wx.cack.dto.BookingSimpleDTO;
import com.wx.cack.model.Booking;
import com.wx.cack.service.BookingService;
import com.wx.cack.util.Paging;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/{userId}/ready/check")
    public ResponseModel bookingReadyCheck(@PathVariable Long userId) {
        return ResponseModel.OK(bookingService.queryReadyCheckBookingByUserId(userId));
    }

    @GetMapping("/{userId}/all")
    public ResponseModel bookingAll(@PathVariable Long userId, int status,
                                    @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        List<BookingSimpleDTO> bookingSimples = bookingService.queryBookingSimpleByUserId(userId, status, pageNum, pageSize);
        return ResponseModel.OK(Paging.setPaging(bookingSimples));
    }

    @GetMapping("/{id}")
    public ResponseModel bookingOne(@PathVariable Long id) {
        return ResponseModel.OK(bookingService.getBookingById(id));
    }

    @GetMapping("/count/status/ready/checking/{userId}")
    public ResponseModel bookingcountReadyAndCheckingStatus(@PathVariable Long userId) {
        return ResponseModel.OK(bookingService.countReadyAndCheckingStatusByUserId(userId));
    }

    @PostMapping
    public ResponseModel bookingCreate(@Valid @RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}/{userId}/cancel")
    public ResponseModel bookingCancelOrder(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseModel.OK(bookingService.cancelBookingByUserIdAndId(id, userId));
    }
}
