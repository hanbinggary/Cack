package com.wx.cack.dao;

import org.springframework.stereotype.Repository;

/**
 * @author liuxuelian
 * @date 2021/04/14
 */
@Repository
public interface UserCouponDao {
    void updateUsedById(Long id);
}
