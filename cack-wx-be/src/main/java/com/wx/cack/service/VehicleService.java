package com.wx.cack.service;

import com.github.pagehelper.PageHelper;
import com.wx.cack.dao.BookingDao;
import com.wx.cack.dao.VehicleDao;
import com.wx.cack.dto.VehicleDTO;
import com.wx.cack.model.Vehicle;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/14
 */
@Service
public class VehicleService {
    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    BookingService bookingService;

    public List<VehicleDTO> listVehicle(Long userId) {
        return vehicleDao.listVehicle(userId);
    }

    public List<VehicleDTO> canBookingListVehicle(Long userId) {
        return vehicleDao.canBookingListVehicle(userId);
    }

    public VehicleDTO getVehicleById(Long id) {
        return vehicleDao.one(id);
    }

    public int countVehicleByUserId(Long userId) {
        return vehicleDao.countVehicleByUserId(userId);
    }

    public ResponseModel insert(Vehicle vehicle) {
        // 判断该用户车牌是否已经登记
        int countVehicleByPlateNo = vehicleDao.countVehicleByPlateNo(vehicle.getUserId(), vehicle.getPlateNo());
        if (countVehicleByPlateNo > 0) {
            return ResponseModel.BAD_REQUEST("该车牌已经登记");
        }
        vehicleDao.insert(vehicle);
        return ResponseModel.OK(vehicle.getId());
    }

    public Long update(Vehicle vehicle) {
        vehicleDao.update(vehicle);
        return vehicle.getId();
    }

    @Transactional
    public Long delete(Long id) {
        System.out.println("id = " + id);
        // 如果车辆有对应的待检测订单，那么强制取消订单
        bookingService.updateCancelReasonOfDeleteVehicleByVehicleId(id);
        vehicleDao.delete(id);
        return id;
    }

}
