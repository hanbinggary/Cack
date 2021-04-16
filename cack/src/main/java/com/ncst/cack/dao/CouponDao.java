package com.ncst.cack.dao;

import com.ncst.cack.dto.CouponDTO;
import com.ncst.cack.model.Coupon;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/08
 */
@Repository
public interface CouponDao {
    List<CouponDTO> listCoupon(int expiredSign, int orderByExpiredTime);

    Long insert(Coupon coupon);

    void updateStatusById(Long id, Boolean status);

    void delete(Long id);

    // 处理优惠券过期
    void updateExpiredByNowTime(LocalDateTime now);
}
