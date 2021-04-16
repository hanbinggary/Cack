package com.ncst.cack.service;

import com.ncst.cack.dao.StatisticsDao;
import com.ncst.cack.dto.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/04
 */
@Service
public class StatisticsService {
    @Autowired
    StatisticsDao statisticsDao;

    /**
     * 统计用户总数、车辆总数和预约总数
     * @return
     */
    @Transactional
    public StatisticsTotalDTO total() {
        StatisticsTotalDTO statisticsTotalDTO = new StatisticsTotalDTO();
        Long userTotal = statisticsDao.countUsers();
        Long vehicleTotal = statisticsDao.countVehicles();
        Long bookingTotal = statisticsDao.countBookings();

        return statisticsTotalDTO.setUserTotal(userTotal)
                .setVehicleTotal(vehicleTotal)
                .setBookingTotal(bookingTotal);

    }

    @Transactional
    public List<StatisticsWeeklyDTO> countWeekly() {
        LocalDate currentDate = LocalDate.now().minusDays(6);
        List<StatisticsWeeklyDTO> weeklyList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            StatisticsWeeklyDTO userResult = statisticsDao.countUserByCreateDate(currentDate);
            userResult = patchWeeklyObject(userResult, currentDate);
            userResult.setName("用户数");
            weeklyList.add(userResult);

            StatisticsWeeklyDTO bookingResult = statisticsDao.countBookingByCreateDate(currentDate);
            bookingResult = patchWeeklyObject(bookingResult, currentDate);
            bookingResult.setName("预约数");
            weeklyList.add(bookingResult);

            currentDate = currentDate.plusDays(1);
        }
        return weeklyList;
    }

    // 封装countWeekly中的判断查询到的是否为空。如果为空，则设置为默认值
    private StatisticsWeeklyDTO patchWeeklyObject(StatisticsWeeklyDTO weekly, LocalDate currentDate) {
        if (weekly == null) {
            weekly = new StatisticsWeeklyDTO();
            weekly.setDate(currentDate.toString().substring(5));
            weekly.setTotal(0L);
        } else {
            weekly.setDate(weekly.getDate().substring(5));
        }
        return weekly;
    }

   @Transactional
   public List<StatisticsWeeklyDTO> countMonth() {
       List<StatisticsWeeklyDTO> months = new ArrayList<>();

       LocalDate currentDate = LocalDate.now().minusYears(1).plusMonths(1);
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

       for (int i = 0; i < 12; i++) {
           String yearMonth = formatter.format(currentDate);
           StatisticsWeeklyDTO userResult = statisticsDao.countUserByCreateMonth(yearMonth);
           if (userResult == null) {
               userResult = new StatisticsWeeklyDTO();
               userResult.setDate(yearMonth);
               userResult.setTotal(0L);
           }
           userResult.setName("用户数");
           months.add(userResult);

           StatisticsWeeklyDTO bookingResult = statisticsDao.countBookingByCreateMonth(yearMonth);
           if (bookingResult == null) {
               bookingResult = new StatisticsWeeklyDTO();
               bookingResult.setDate(yearMonth);
               bookingResult.setTotal(0L);
           }
           bookingResult.setName("预约数");
           months.add(bookingResult);

           currentDate = currentDate.plusMonths(1);
       }
       return months;
   }


    public List<StatisticsGenderDTO> countGender() {
        return statisticsDao.countGender();
    }

    public List<StatisticsVehicleTypeDTO> countVehicleType() {
        return statisticsDao.countVehicleType();
    }
}
