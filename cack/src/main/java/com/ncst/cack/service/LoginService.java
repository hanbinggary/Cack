package com.ncst.cack.service;

import com.ncst.cack.dao.LoginDao;
import com.ncst.cack.model.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Service
public class LoginService implements UserDetailsService {
    @Autowired
    LoginDao loginDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.length() == 0) {
            throw new UsernameNotFoundException("用户名不能为空");
        }

        LoginInfo loginInfo = loginDao.queryByUsername(username);
        if (loginInfo == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return loginInfo;
    }
}
