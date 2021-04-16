package com.ncst.cack.controller;

import com.ncst.cack.dto.CouponDTO;
import com.ncst.cack.model.Coupon;
import com.ncst.cack.qo.CouponQO;
import com.ncst.cack.service.CouponService;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/08
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @GetMapping
    public ResponseModel couponList(CouponQO couponQO) {
        List<CouponDTO> coupons = couponService.listCoupon(couponQO);
        return ResponseModel.OK(Paging.setPaging(coupons));
    }

    @PostMapping
    public ResponseModel couponInsert(@RequestBody Coupon coupon) {
        int year = coupon.getDate().getYear();
        int month = coupon.getDate().getMonthValue();
        int day = coupon.getDate().getDayOfMonth();
        coupon.setExpiredTime(LocalDateTime.of(year, month, day, coupon.getHours(), coupon.getMinutes(), coupon.getMinutes()));
        return ResponseModel.OK(couponService.insert(coupon));
    }

    @PutMapping("/{id}/status")
    public ResponseModel couponUpdateStatus(@PathVariable Long id,  Boolean status) {
        couponService.updateStatusById(id, status);
        return ResponseModel.OK();
    }

    @DeleteMapping("/{id}")
    public ResponseModel couponDeleteById(@PathVariable Long id) {
        couponService.deleteCouponById(id);
        return ResponseModel.OK();
    }
}
