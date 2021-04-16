package com.wx.cack.dao;

import com.wx.cack.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/12
 */
@Repository
public interface UserDao {

    User queryUserByOpenid(String openid);

    void insert(User user);

    void updateLastLoginTimeById(Long id, LocalDateTime now);
}
