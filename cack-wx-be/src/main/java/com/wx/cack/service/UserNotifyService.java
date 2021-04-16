package com.wx.cack.service;

import com.wx.cack.dao.UserNotifyDao;
import com.wx.cack.dto.UserNotifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Service
public class UserNotifyService {
    @Autowired
    UserNotifyDao userNotifyDao;

    public List<UserNotifyDTO> queryUnReadNotifyByUserId(Long userId) {
        return userNotifyDao.queryUnReadNotifyByUserId(userId);
    }


    public List<UserNotifyDTO> queryReadedNotifyByUserId(Long userId) {
        return userNotifyDao.queryReadedNotifyByUserId(userId);
    }

    public UserNotifyDTO getNotifyById(Long id) {
        return userNotifyDao.one(id);
    }

    public int countUnReadNotifyByUserId(Long userId) {
        return userNotifyDao.countUnReadNotifyByUserId(userId);
    }

    public Long updateReadedById(Long id) {
        userNotifyDao.updateReadedById(id, LocalDateTime.now());
        return id;
    }
}
