package com.ncst.cack.controller;

import com.ncst.cack.dto.NotifyDTO;
import com.ncst.cack.model.Notify;
import com.ncst.cack.service.NotifyService;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@RestController
@RequestMapping("/notify")
public class NotifyController {
    @Autowired
    NotifyService notifyService;

    @GetMapping
    public ResponseModel notifyList(@RequestParam(name = "orderByGmtCreate", defaultValue = "0") int orderByGmtCreate,
                                    @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        List<NotifyDTO> notifyList = notifyService.listNotify(orderByGmtCreate, pageNum, pageSize);
        return ResponseModel.OK(Paging.setPaging(notifyList));
    }

    @PostMapping
    public ResponseModel notifyInsert(@Valid @RequestBody Notify notify) {
        return ResponseModel.OK(notifyService.insert(notify));
    }

    @DeleteMapping("/{id}")
    public ResponseModel notifyDelete(@PathVariable Long id) {
        notifyService.delete(id);
        return ResponseModel.OK();
    }
}
