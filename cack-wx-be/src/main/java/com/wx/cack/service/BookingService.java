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
        // 判断该时段是否还可以预约
        int maxTimeBookingTotal = stationDao.queryMaxTimeBookingTotal();

        BookingTime bookingTime = bookingTimeDao.one(booking.getTimeId());
        if (bookingTime.getTotal() >= maxTimeBookingTotal) {
            return ResponseModel.FAILED("预约失败，请重新预约");
        }
        // 预约时间段总数加1
        bookingTimeDao.addOneTotalById(booking.getTimeId());

        booking.setOrderNo(generateOrderNo(booking.getUserId()));
        // 如果使用了优惠券，优惠券设置为使用
        if (booking.getCouponId() != -1) {
            userCouponDao.updateUsedById(booking.getCouponId());
        } else {
            booking.setCouponId(null);
        }
        // 创建预约
        bookingDao.createBooking(booking);
        // 预约成功发送成功消息
        Notify notify = new Notify();
        notify.setTitle("预约成功");
        notify.setContent("订单号为" + booking.getOrderNo() + "成功预约" + bookingTime.getDate() + " " +
                bookingTime.getStartTime() + ":00 ~ " + bookingTime.getEndTime() + ":00时段进行车检。");
        notifyDao.insert(notify);
        userNotifyDao.insert(booking.getUserId(), notify.getId());

        return ResponseModel.OK(booking.getId());
    }

    // 生成订单号 20个数字
    // 用户ID + 日期 202104121424
    private String generateOrderNo(Long userId) {
        StringBuilder orderNo = new StringBuilder(userId.toString());
        while (orderNo.length() < 8) {
            orderNo.append( (int) Math.floor(Math.random() * 9 + 1));
        }
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        orderNo.append(datetime);
        return orderNo.toString();
    }

    // 用户取消订单
    @Transactional
    public Long cancelBookingByUserIdAndId(Long id, Long userId) {
        // 当订单为待检测状态时，判断预约时段是否过期，如未过期，总数减1；否则，默认
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
        // 当订单为待检测状态时，判断预约时段是否过期，如未过期，总数减1；否则，默认
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
        String cancelReason = "车辆不存在，订单取消";
        bookingDao.updateCancelReasonOfDeleteVehicleByVehicleId(vehicleId, cancelReason);
    }
}
