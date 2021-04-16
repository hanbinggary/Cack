package com.ncst.cack.dao;

import com.ncst.cack.model.LoginInfo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Repository
public interface LoginDao {
    LoginInfo queryByUsername(String username);

    void updateLastLoginTimeById(Long id, LocalDateTime lastLoginTime);
}
