package com.ncst.cack.dao;

import com.ncst.cack.dto.StatisticsDateDTO;
import com.ncst.cack.dto.StatisticsGenderDTO;
import com.ncst.cack.dto.StatisticsVehicleTypeDTO;
import com.ncst.cack.dto.StatisticsWeeklyDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/04
 */
@Repository
public interface StatisticsDao {
    Long countBookings();

    Long countVehicles();

    Long countUsers();

    StatisticsWeeklyDTO countUserByCreateDate(LocalDate currentDate);

    StatisticsWeeklyDTO countBookingByCreateDate(LocalDate currentDate);

    StatisticsWeeklyDTO countUserByCreateMonth(String month);

    StatisticsWeeklyDTO countBookingByCreateMonth(String month);

    List<StatisticsGenderDTO> countGender();

    List<StatisticsVehicleTypeDTO> countVehicleType();
}
