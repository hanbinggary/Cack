package com.wx.cack.dao;

import com.wx.cack.dto.BookingDTO;
import com.wx.cack.dto.BookingSimpleDTO;
import com.wx.cack.dto.ReadyCheckDTO;
import com.wx.cack.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/13
 */
@Repository
public interface BookingDao {
    List<ReadyCheckDTO> queryReadyCheckBookingByUserId(Long userId);

    List<BookingSimpleDTO> queryBookingSimpleByUserId(Long userId, int status);

    Long queryBookingTimeIdById(Long id);

    Long queryBookingIdByVehicleId(Long vehicleId);

    BookingDTO one(Long id);

    int countStatusByUserId(Long userId, int status);

    void createBooking(Booking booking);

    void cancelBookingByUserIdAndId(Long id, Long userId);

    void updateCancelReasonOfDeleteVehicleByVehicleId(Long vehicleId, String cancelReason);
}
