package com.wx.cack.service;

import com.github.pagehelper.PageHelper;
import com.wx.cack.dao.*;
import com.wx.cack.dto.BookingCountStatusDTO;
import com.wx.cack.dto.BookingDTO;
import com.wx.cack.dto.BookingSimpleDTO;
import com.wx.cack.dto.ReadyCheckDTO;
import com.wx.cack.model.Booking;
import com.wx.cack.model.BookingTime;
import com.wx.cack.model.Notify;
import com.wx.cack.util.ResponseModel;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Service
public class BookingService {
    @Autowired
    BookingDao bookingDao;

    @Autowired
    BookingTimeDao bookingTimeDao;

    @Autowired
    UserCouponDao userCouponDao;

    @Autowired
    StationDao stationDao;

    @Autowired
    NotifyDao notifyDao;

    @Autowired
    UserNotifyDao userNotifyDao;

    public List<ReadyCheckDTO> queryReadyCheckBookingByUserId(Long userId) {
        return bookingDao.queryReadyCheckBookingByUserId(userId);
    }

    public List<BookingSimpleDTO> queryBookingSimpleByUserId(Long userId, int status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return bookingDao.queryBookingSimpleByUserId(userId, status);
    }

    public BookingDTO getBookingById(Long id) {
        return bookingDao.one(id);
    }

    @Transactional
    public BookingCountStatusDTO countReadyAndCheckingStatusByUserId(Long userId) {
        BookingCountStatusDTO bookingCountStatus = new BookingCountStatusDTO();
        bookingCountStatus.setReadyCheckTotal(bookingDao.countStatusByUserId(userId, 1));
        bookingCountStatus.setCheckingTotal(bookingDao.countStatusByUserId(userId, 2));
        return bookingCountStatus;
    }

    @Transactional
    public synchronized ResponseModel createBooking(Booking booking) {
        // ????????????????????????????????????
        int maxTimeBookingTotal = stationDao.queryMaxTimeBookingTotal();

        BookingTime bookingTime = bookingTimeDao.one(booking.getTimeId());
        if (bookingTime.getTotal() >= maxTimeBookingTotal) {
            return ResponseModel.FAILED("??????????????????????????????");
        }
        // ????????????????????????1
        bookingTimeDao.addOneTotalById(booking.getTimeId());

        booking.setOrderNo(generateOrderNo(booking.getUserId()));
        // ???????????????????????????????????????????????????
        if (booking.getCouponId() != -1) {
            userCouponDao.updateUsedById(booking.getCouponId());
        } else {
            booking.setCouponId(null);
        }
        // ????????????
        bookingDao.createBooking(booking);
        // ??????????????????????????????
        Notify notify = new Notify();
        notify.setTitle("????????????");
        notify.setContent("????????????" + booking.getOrderNo() + "????????????" + bookingTime.getDate() + " " +
                bookingTime.getStartTime() + ":00 ~ " + bookingTime.getEndTime() + ":00?????????????????????");
        notifyDao.insert(notify);
        userNotifyDao.insert(booking.getUserId(), notify.getId());

        return ResponseModel.OK(booking.getId());
    }

    // ??????????????? 20?????????
    // ??????ID + ?????? 202104121424
    private String generateOrderNo(Long userId) {
        StringBuilder orderNo = new StringBuilder(userId.toString());
        while (orderNo.length() < 8) {
            orderNo.append( (int) Math.floor(Math.random() * 9 + 1));
        }
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        orderNo.append(datetime);
        return orderNo.toString();
    }

    // ??????????????????
    @Transactional
    public Long cancelBookingByUserIdAndId(Long id, Long userId) {
        // ??????????????????????????????????????????????????????????????????????????????????????????1??????????????????
        Long bookingTimeId = bookingDao.queryBookingTimeIdById(id);
        if (bookingTimeId != null) {
            BookingTime bookingTime = bookingTimeDao.one(bookingTimeId);
            if (bookingTime != null && bookingTime.getStatus()) {
                if (bookingTime.getDate().isAfter(LocalDate.now())) {
                    bookingTimeDao.subOneTotalById(bookingTimeId);
                } else if (bookingTime.getDate().equals(LocalDate.now())) {
                    if (bookingTime.getStartTime() > LocalDateTime.now().getHour()) {
                        bookingTimeDao.subOneTotalById(bookingTimeId);
                    }
                }
            }
        }
        bookingDao.cancelBookingByUserIdAndId(id, userId);
        return id;
    }

    @Transactional
    public void updateCancelReasonOfDeleteVehicleByVehicleId(Long vehicleId) {
        // ??????????????????????????????????????????????????????????????????????????????????????????1??????????????????
        Long bookingId = bookingDao.queryBookingIdByVehicleId(vehicleId);
        if (bookingId != null) {
            Long bookingTimeId = bookingDao.queryBookingTimeIdById(bookingId);
            if (bookingTimeId != null) {
                BookingTime bookingTime = bookingTimeDao.one(bookingTimeId);
                if (bookingTime != null && bookingTime.getStatus()) {
                    if (bookingTime.getDate().isAfter(LocalDate.now())) {
                        bookingTimeDao.subOneTotalById(bookingTimeId);
                    } else if (bookingTime.getDate().equals(LocalDate.now())) {
                        if (bookingTime.getStartTime() > LocalDateTime.now().getHour()) {
                            bookingTimeDao.subOneTotalById(bookingTimeId);
                        }
                    }
                }
            }
        }
        String cancelReason = "??????????????????????????????";
        bookingDao.updateCancelReasonOfDeleteVehicleByVehicleId(vehicleId, cancelReason);
    }
}
