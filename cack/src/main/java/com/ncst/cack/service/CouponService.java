package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.CouponDao;
import com.ncst.cack.dao.UserCouponDao;
import com.ncst.cack.dao.UserDao;
import com.ncst.cack.dto.CouponDTO;
import com.ncst.cack.model.Coupon;
import com.ncst.cack.qo.CouponQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/08
 */
@Service
public class CouponService {
    @Autowired
    CouponDao couponDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserCouponDao userCouponDao;

    public List<CouponDTO> listCoupon(CouponQO couponQO) {
        PageHelper.startPage(couponQO.getPageNum(), couponQO.getPageSize());
        return couponDao.listCoupon(couponQO.getExpiredSign(), couponQO.getOrderByExpiredTime());
    }

    @Transactional
    public Long insert(Coupon coupon) {
        couponDao.insert(coupon);

        // 将优惠券发布给所有可用的用户
        List<Long> usageUserIds = userDao.queryUsageUserIds();
        userCouponDao.insertBatch(usageUserIds, coupon.getId());

        return coupon.getId();
    }

    public void updateStatusById(Long id, Boolean status) {
        couponDao.updateStatusById(id, status);
    }

    public void deleteCouponById(Long id) {
        // 删除已经领取的用户的记录
        userCouponDao.deleteByCouponId(id);

        couponDao.delete(id);
    }
}
