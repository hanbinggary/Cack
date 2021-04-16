package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.UserNotifyDao;
import com.ncst.cack.dto.UserNotifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@Service
public class UserNotifyService {
    @Autowired
    UserNotifyDao userNotifyDao;

    public List<UserNotifyDTO> listUserNotify(Long notifyId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userNotifyDao.listUserNotify(notifyId);
    }
}
