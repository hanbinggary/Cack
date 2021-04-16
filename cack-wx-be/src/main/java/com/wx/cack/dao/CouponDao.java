package com.wx.cack.dao;

import com.wx.cack.dto.CouponUnusedDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/14
 */
@Repository
public interface CouponDao {
    List<CouponUnusedDTO> queryUnusedCouponByUserId(Long userId);

    int countUsableCouponByUserId(Long userId);
}
