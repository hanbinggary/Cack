package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.BookingDao;
import com.ncst.cack.dto.BookingDTO;
import com.ncst.cack.qo.BookingQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/07
 */
@Service
public class BookingService {
    @Autowired
    BookingDao bookingDao;

    public List<BookingDTO> listBooking(BookingQO bookingQO) {
        PageHelper.startPage(bookingQO.getPageNum(), bookingQO.getPageSize());
        return bookingDao.listBooking(bookingQO);
    }

    public BookingDTO getBookingById(Long id) {
        return bookingDao.one(id);
    }

    @Transactional
    public int updateStatusById(Long id, int status) {
        if (status == 2) { // 进行车检
            bookingDao.updateStartCheckTimeById(id, LocalDateTime.now());
        } else if (status == 3) {
            bookingDao.updateEndCheckTimeById(id, LocalDateTime.now());
        }
        bookingDao.updateStatusById(id, status);
        return status;
    }

    public Long updateCancelReasonById(Long id, String cancelReason) {
        bookingDao.updateCancelReasonById(id, cancelReason);
        return id;
    }
}
