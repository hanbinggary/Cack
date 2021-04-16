package com.wx.cack.dao;

import com.wx.cack.dto.BookingTimeDTO;
import com.wx.cack.model.BookingTime;
import com.wx.cack.model.BookingTimeDate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Repository
public interface BookingTimeDao {
    BookingTimeDate queryTodayDateTotal(LocalDate now, int startTime, int maxTimeBookingTotal);

    List<BookingTimeDate> queryDateTotal(LocalDate now, int maxTimeBookingTotal);

    List<BookingTimeDTO> queryTimeByDateAndMaxBookingTotal(LocalDate date, int maxTimeBookingTotal);

    BookingTime one(Long id);

    void addOneTotalById(Long id);

    void subOneTotalById(Long id);
}
