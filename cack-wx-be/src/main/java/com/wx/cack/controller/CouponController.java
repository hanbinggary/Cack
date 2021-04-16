package com.wx.cack.controller;

import com.wx.cack.service.CouponService;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxuelian
 * @date 2021/04/14
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @GetMapping("/unused/{userId}")
    public ResponseModel couponUnused(@PathVariable Long userId) {
        return ResponseModel.OK(couponService.queryUnusedCouponByUserId(userId));
    }

    @GetMapping("/count/usable/{userId}")
    public ResponseModel couponCountUsable(@PathVariable Long userId) {
        return ResponseModel.OK(couponService.countUsableCouponByUserId(userId));
    }
}
