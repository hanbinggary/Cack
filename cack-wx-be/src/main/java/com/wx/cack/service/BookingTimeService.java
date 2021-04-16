package com.wx.cack.service;

import com.wx.cack.dao.BookingTimeDao;
import com.wx.cack.dao.StationDao;
import com.wx.cack.dto.BookingTimeDTO;
import com.wx.cack.dto.BookingTimeDateDTO;
import com.wx.cack.model.BookingTimeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Service
public class BookingTimeService {
    @Autowired
    BookingTimeDao bookingTimeDao;

    @Autowired
    StationDao stationDao;

    @Transactional
    public List<BookingTimeDateDTO> getDateTotal() {
        int maxTimeBookingTotal = stationDao.queryMaxTimeBookingTotal();
        List<BookingTimeDateDTO> bookingTimeDateDTOList = new ArrayList<>();
        // 查找当天的
        BookingTimeDate todayDateTotal = bookingTimeDao.queryTodayDateTotal(LocalDate.now(), LocalDateTime.now().getHour(), maxTimeBookingTotal);
        if (todayDateTotal != null && todayDateTotal.getTimeCount() > 0) {
            BookingTimeDateDTO bookingTimeDateDTO = new BookingTimeDateDTO();
            bookingTimeDateDTO.setDate(todayDateTotal.getDate())
                    .setTimeCount(todayDateTotal.getTimeCount())
                    .setTotals(todayDateTotal.getTotals())
                    .setDegreeOfCrowding(
                            calculateDegreeOfCrowding(todayDateTotal.getTimeCount(), todayDateTotal.getTotals(), maxTimeBookingTotal));
            bookingTimeDateDTOList.add(bookingTimeDateDTO);
        }

        List<BookingTimeDate> bookingTimeDates = bookingTimeDao.queryDateTotal(LocalDate.now(), maxTimeBookingTotal);
        for (BookingTimeDate bookingTimeDate : bookingTimeDates) {
            String degreeOfCrowding =
                    calculateDegreeOfCrowding(bookingTimeDate.getTimeCount(), bookingTimeDate.getTotals(), maxTimeBookingTotal);
            if (bookingTimeDate.getTimeCount() > 0) {
                BookingTimeDateDTO bookingTimeDateDTO = new BookingTimeDateDTO();
                bookingTimeDateDTO.setDate(bookingTimeDate.getDate())
                        .setTimeCount(bookingTimeDate.getTimeCount())
                        .setTotals(bookingTimeDate.getTotals())
                        .setDegreeOfCrowding(degreeOfCrowding);
                bookingTimeDateDTOList.add(bookingTimeDateDTO);
            }
        }
        return bookingTimeDateDTOList;
    }

    // 根据日期计算拥挤程度
    private String calculateDegreeOfCrowding(int timeCount, int total, int maxTimeBookingTotal) {
        double crowdingIndex = 1.0 * total / (timeCount * maxTimeBookingTotal);
        if (crowdingIndex <= (1.0 / 3)) {
            return "推荐";
        } else if (crowdingIndex <= (2.0 / 3)) {
            return "拥挤";
        } else {
            return "爆满";
        }
    }

    public List<BookingTimeDTO> queryTimeByDate(LocalDate date) {
        int maxTimeBookingTotal = stationDao.queryMaxTimeBookingTotal();
        List<BookingTimeDTO> bookingTimeDTOList = bookingTimeDao.queryTimeByDateAndMaxBookingTotal(date, maxTimeBookingTotal);
        for (BookingTimeDTO bookingTimeDTO : bookingTimeDTOList) {
            bookingTimeDTO.setDegreeOfCrowding(calculateDegreeOfCrowding(bookingTimeDTO.getTotal(), maxTimeBookingTotal));
        }
        return bookingTimeDTOList;
    }

    // 根据时间计算拥挤程度
    private String calculateDegreeOfCrowding(int total, int maxTimeBookingTotal) {
        double crowdingIndex = 1.0 * total /  maxTimeBookingTotal;
        if (crowdingIndex <= (1.0 / 3)) {
            return "推荐";
        } else if (crowdingIndex <= (2.0 / 3)) {
            return "拥挤";
        } else {
            return "爆满";
        }
    }
}
