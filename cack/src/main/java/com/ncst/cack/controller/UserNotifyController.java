package com.ncst.cack.controller;

import com.ncst.cack.dto.UserNotifyDTO;
import com.ncst.cack.service.UserNotifyService;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@RestController
@RequestMapping("/user/notify")
public class UserNotifyController {
    @Autowired
    UserNotifyService userNotifyService;

    @GetMapping("/{notifyId}")
    public ResponseModel userNotifyList(@PathVariable Long notifyId,
                                        @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        List<UserNotifyDTO> listUserNotify = userNotifyService.listUserNotify(notifyId, pageNum, pageSize);
        return ResponseModel.OK(Paging.setPaging(listUserNotify));
    }
}
