package com.ncst.cack.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Repository
public interface UserCouponDao {

    void insertBatch(List<Long> userIds, Long couponId);

    void deleteByCouponId(Long couponId);
}
