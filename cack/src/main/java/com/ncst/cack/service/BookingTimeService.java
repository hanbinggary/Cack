package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.BookingDao;
import com.ncst.cack.dao.BookingTimeDao;
import com.ncst.cack.dto.BookingTimeDTO;
import com.ncst.cack.model.BookingTime;
import com.ncst.cack.qo.BookingTimeDeleteBatchQO;
import com.ncst.cack.qo.BookingTimeInsertQO;
import com.ncst.cack.qo.BookingTimeQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Service
public class BookingTimeService {
    @Autowired
    BookingTimeDao bookingTimeDao;

    @Autowired
    BookingDao bookingDao;

    public List<BookingTimeDTO> listBookingTime(BookingTimeQO qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return bookingTimeDao.listBookingTime(qo);
    }

    public BookingTimeDTO getBookingTimeById(Long id) {
        return bookingTimeDao.one(id);
    }

    public List<Long> insertBatch(BookingTimeInsertQO qo) {
        List<Long> insertIds = new ArrayList<>();

        int start = qo.getStartTime();
        int end = qo.getEndTime();
        LocalDate current = qo.getStartDate();
        LocalDate endDate = qo.getEndDate();
        List<String> weeklyList = qo.getWeeklyList();
        List<BookingTime> bookingTimes = new ArrayList<>();

        while (!current.isAfter(endDate)) {
            if (weeklyList.contains(current.getDayOfWeek().toString())) {
                for (int i = start; i < end; i++) {
                    if (bookingTimeDao.countByDateAndTime(current, i) == 0) {
                        bookingTimes.add(new BookingTime()
                                .setDate(current)
                                .setStartTime(i)
                                .setEndTime(i + 1));
                    }
                }
            }
            current = current.plusDays(1);
        }

        if (bookingTimes.size() > 0) {
            bookingTimeDao.insertBatch(bookingTimes);
            for (BookingTime bookingTime : bookingTimes) {
                insertIds.add(bookingTime.getId());
            }
        }

        return insertIds;
    }

    public void updateCountAddOneById(Long id) {
        bookingTimeDao.updateCountAddOneById(id);
    }

   @Transactional
    public void updateStatusById(Long id, Boolean status, String invalidReason) {
        if (status == false) {
            // 修改订单取消原因
            bookingDao.updateCancelReasonByBookingTimeId(id, invalidReason);
            bookingTimeDao.updateStatusById(id, status, invalidReason);
        }
    }


    public void delete(Long id, String invalidReason) {
        // 修改订单取消原因
        bookingDao.updateCancelReasonByBookingTimeId(id, invalidReason);
        bookingTimeDao.delete(id, invalidReason);
    }

    public void deleteBatch(BookingTimeDeleteBatchQO qo) {
        // 修改订单取消原因
        bookingDao.updateBatchCancelReasonByBookingTimeIds(qo.getIds(), qo.getInvalidReason());
        bookingTimeDao.deleteBatch(qo.getIds(), qo.getInvalidReason());
    }
}
