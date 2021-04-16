package com.ncst.cack.dao;

import com.ncst.cack.dto.BookingDTO;
import com.ncst.cack.qo.BookingQO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/07
 */
@Repository
public interface BookingDao {
    List<BookingDTO> listBooking(BookingQO bookingQO);

    BookingDTO one(Long id);

    void updateStatusById(Long id, int status);

    void updateStartCheckTimeById(Long id, LocalDateTime startCheckTime);

    void updateEndCheckTimeById(Long id, LocalDateTime endCheckTime);

    void updateCancelReasonById(Long id, String cancelReason);

    void updateCancelReasonByBookingTimeId(Long bookingTimeId, String cancelReason);

    void updateBatchCancelReasonByBookingTimeIds(List<Long> bookingTimeIds, String cancelReason);

    void updateBatchStatusAndCancelReasonByBookingTimeIds(List<Long> bookingTimeIds);
}
