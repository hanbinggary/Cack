package com.ncst.cack.dao;

import com.ncst.cack.dto.BookingTimeDTO;
import com.ncst.cack.model.BookingTime;
import com.ncst.cack.qo.BookingTimeQO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Repository
public interface BookingTimeDao {

    List<BookingTimeDTO> listBookingTime(BookingTimeQO qo);

    BookingTimeDTO one(Long id);

    int countByDateAndTime(LocalDate date, int startTime);

    void insertBatch(List<BookingTime> bookingTimes);

    void updateCountAddOneById(Long id);

    void updateStatusById(Long id, Boolean status, String invalidReason);

    void delete(Long id, String invalidReason);

    void deleteBatch(List<Long> ids, String invalidReason);

    // 处理预约过期
    List<Long> queryIdsByDate(LocalDate now);
    // 更新过期时间的状态为无效
    void updateExpiredDateStatus(LocalDate now);
}
