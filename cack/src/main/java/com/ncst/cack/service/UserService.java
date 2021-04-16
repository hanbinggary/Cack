package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.dao.UserDao;
import com.ncst.cack.dto.UserDTO;
import com.ncst.cack.qo.UserQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<UserDTO> listUser(UserQO userQO) {
        PageHelper.startPage(userQO.getPageNum(), userQO.getPageSize());
        return userDao.listUser(userQO);
    }

    public UserDTO getUserById(Long id) {
        return userDao.one(id);
    }

    public void updateStatusById(Long id, Boolean status) {
        userDao.updateStatusById(id, status);
    }
}
