package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.NotifyDao;
import com.ncst.cack.dao.UserDao;
import com.ncst.cack.dao.UserNotifyDao;
import com.ncst.cack.dto.NotifyDTO;
import com.ncst.cack.model.Notify;
import com.ncst.cack.model.UserNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@Service
public class NotifyService {
    @Autowired
    NotifyDao notifyDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserNotifyDao userNotifyDao;

    public List<NotifyDTO> listNotify(int orderByGmtCreate, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return notifyDao.listNotify(orderByGmtCreate);
    }

    @Transactional
    public Long insert(Notify notify) {
        notifyDao.insert(notify);

        // 查找所有用户，并添加到用户消息表
        List<Long> userIds = userDao.listIds();
        List<UserNotify> userNotifies = new ArrayList<>();
        for (Long userId : userIds) {
            userNotifies.add(new UserNotify().setUserId(userId).setNotifyId(notify.getId()));
        }
        userNotifyDao.insertBatch(userNotifies);

        return notify.getId();
    }

    @Transactional
    public void delete(Long id) {
        // 删除用户通知关联表的消息
        userNotifyDao.delete(id);
        notifyDao.delete(id);
    }
}
