package com.ncst.cack.controller;

import com.ncst.cack.dto.BookingTimeDTO;
import com.ncst.cack.qo.BookingTimeDeleteBatchQO;
import com.ncst.cack.qo.BookingTimeInsertQO;
import com.ncst.cack.qo.BookingTimeQO;
import com.ncst.cack.service.BookingTimeService;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@RestController
@RequestMapping("/booking/time")
@Validated
public class BookingTimeController {
    @Autowired
    BookingTimeService bookingTimeService;

    @GetMapping
    public ResponseModel bookingTimeList(BookingTimeQO qo) {
        System.out.println("qo = " + qo);
        List<BookingTimeDTO> bookingTimes = bookingTimeService.listBookingTime(qo);
        return ResponseModel.OK(Paging.setPaging(bookingTimes));
    }

    @GetMapping("/{id}")
    public ResponseModel bookingTimeOne(@PathVariable Long id) {
        return ResponseModel.OK(bookingTimeService.getBookingTimeById(id));
    }

    @PostMapping
    public ResponseModel bookingTimeInsertBatch(@Valid @RequestBody BookingTimeInsertQO qo) {
        System.out.println("qo = " + qo);
        return ResponseModel.OK(bookingTimeService.insertBatch(qo));
    }

    @PutMapping("/{id}/status")
    public ResponseModel bookingTimeUpdateStatusById(@PathVariable Long id,
                                                     @NotNull(message = "状态不能为空") Boolean status,
                                                     @RequestParam(name = "invalidReason", defaultValue = "") String invalidReason) {
        bookingTimeService.updateStatusById(id, status, invalidReason);
        return ResponseModel.OK();
    }

    @DeleteMapping("/{id}")
    public ResponseModel bookingTimeDelete(@PathVariable Long id, String invalidReason) {
        bookingTimeService.delete(id, invalidReason);
        return ResponseModel.OK();
    }

    @DeleteMapping("/batch")
    public ResponseModel bookingTimeDeleteBatch(@RequestBody BookingTimeDeleteBatchQO qo) {
        bookingTimeService.deleteBatch(qo);
        return ResponseModel.OK();
    }
}
