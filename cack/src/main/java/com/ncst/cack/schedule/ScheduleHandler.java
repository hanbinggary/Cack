package com.ncst.cack.schedule;

import com.ncst.cack.dao.BookingDao;
import com.ncst.cack.dao.BookingTimeDao;
import com.ncst.cack.dao.CouponDao;
import com.ncst.cack.dto.CouponDTO;
import com.ncst.cack.service.BookingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时器处理器
 * @author liuxuelian
 * @date 2021/04/12
 */
@Configuration
@EnableScheduling
public class ScheduleHandler {

    @Autowired
    CouponDao couponDao;

    @Autowired
    BookingTimeDao bookingTimeDao;

    @Autowired
    BookingDao bookingDao;

    /**
     * 每分钟进行优惠券过期检查，过期了设置为过期
     */
   @Scheduled(cron = "0 0/1 * * * *")
    public void couponExpiredExecute() {
        couponDao.updateExpiredByNowTime(LocalDateTime.now());
   }

    /**
     * 预约时间过了，取消订单
     */
   @Scheduled(cron = "59 59 23 ? * *")
    public void bookingTimeExpiredExecute() {
       bookingTimeDao.updateExpiredDateStatus(LocalDate.now());
       List<Long> bookingTimeIds = bookingTimeDao.queryIdsByDate(LocalDate.now());
       bookingDao.updateBatchStatusAndCancelReasonByBookingTimeIds(bookingTimeIds);
   }
}
