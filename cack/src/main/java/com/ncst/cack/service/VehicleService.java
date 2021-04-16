package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.VehicleDao;
import com.ncst.cack.dto.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/07
 */
@Service
public class VehicleService {
    @Autowired
    VehicleDao vehicleDao;

    public List<VehicleDTO> listVehicle(String searchValue, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return vehicleDao.listVehicle(searchValue);
    }

    public VehicleDTO getVehicleById(Long id) {
        return vehicleDao.one(id);
    }
}
