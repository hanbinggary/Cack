package com.wx.cack.service;

import com.wx.cack.dao.CouponDao;
import com.wx.cack.dto.CouponUnusedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/14
 */
@Service
public class CouponService {
    @Autowired
    CouponDao couponDao;

    public List<CouponUnusedDTO> queryUnusedCouponByUserId(Long userId) {
        return couponDao.queryUnusedCouponByUserId(userId);
    }

    public int countUsableCouponByUserId(Long userId) {
        return couponDao.countUsableCouponByUserId(userId);
    }
}
