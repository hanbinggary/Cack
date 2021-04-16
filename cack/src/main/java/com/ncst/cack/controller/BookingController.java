package com.ncst.cack.controller;

import com.ncst.cack.dto.BookingDTO;
import com.ncst.cack.qo.BookingQO;
import com.ncst.cack.service.BookingService;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/07
 */
@RestController
@RequestMapping("/booking")
@Validated
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseModel bookingList(BookingQO bookingQO) {
        List<Integer> filterByOrderStatusList = new ArrayList<>();
        if (bookingQO.getFilterByOrderStatus().length() > 0) {
            String[] status = bookingQO.getFilterByOrderStatus().split(",");
            for (String s : status) {
                filterByOrderStatusList.add(Integer.parseInt(s));
            }
        } else {
            filterByOrderStatusList.add(0);
            filterByOrderStatusList.add(1);
            filterByOrderStatusList.add(2);
            filterByOrderStatusList.add(3);
            filterByOrderStatusList.add(4);
        }
        bookingQO.setFilterByOrderStatusList(filterByOrderStatusList);
        List<BookingDTO> bookingList = bookingService.listBooking(bookingQO);

        return ResponseModel.OK(Paging.setPaging(bookingList));
    }

    @GetMapping("/{id}")
    public ResponseModel bookingOne(@PathVariable Long id) {
        return ResponseModel.OK(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseModel bookingUpdateStatus(@PathVariable Long id, @NotNull(message = "状态不能为空") int status) {
        return ResponseModel.OK(bookingService.updateStatusById(id, status));
    }

    @PutMapping("/{id}/cancel/reason")
    public ResponseModel bookingUpdateCancelReason(@PathVariable Long id, @NotNull(message = "取消原因不能为空") String cancelReason) {
        return ResponseModel.OK(bookingService.updateCancelReasonById(id, cancelReason));
    }
}
