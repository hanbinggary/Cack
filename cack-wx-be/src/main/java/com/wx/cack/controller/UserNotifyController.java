package com.wx.cack.controller;

import com.wx.cack.dto.UserNotifyDTO;
import com.wx.cack.service.UserNotifyService;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@RestController
@RequestMapping("/notify")
public class UserNotifyController {
    @Autowired
    UserNotifyService userNotifyService;

    @GetMapping("/query/unread/{userId}")
    public ResponseModel notifyUnRead(@PathVariable Long userId) {
        return ResponseModel.OK(userNotifyService.queryUnReadNotifyByUserId(userId));
    }

    @GetMapping("/query/readed/{userId}")
    public ResponseModel notifyReaded(@PathVariable Long userId) {
        return ResponseModel.OK(userNotifyService.queryReadedNotifyByUserId(userId));
    }

    @GetMapping("/count/unread/{userId}")
    public ResponseModel userNotifyCountUnread(@PathVariable Long userId) {
        return ResponseModel.OK(userNotifyService.countUnReadNotifyByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseModel userNotifyOne(@PathVariable Long id) {
        return ResponseModel.OK(userNotifyService.getNotifyById(id));
    }

    @PutMapping("/{id}/readed")
    public ResponseModel userNotifyUpdateReaded(@PathVariable Long id) {
        return ResponseModel.OK(userNotifyService.updateReadedById(id));
    }
}
