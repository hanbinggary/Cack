package com.wx.cack.service;

import com.wx.cack.dao.UserDao;
import com.wx.cack.model.Login;
import com.wx.cack.model.User;
import com.wx.cack.qo.LoginQO;
import com.wx.cack.util.ResponseModel;
import com.wx.cack.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/12
 */
@Service
public class LoginService {
    @Autowired
    UserDao userDao;

    @Transactional
    public ResponseModel login(LoginQO loginQO) throws IOException {
        Login login = WeChatUtil.httpsRequest(loginQO.getCode());
        if (login != null) {
            User loginUser = userDao.queryUserByOpenid(login.getOpenid());
            if (loginUser != null) {
                if (!loginUser.getStatus()) {
                    return ResponseModel.BAD_REQUEST("该用户已停用");
                }
                userDao.updateLastLoginTimeById(loginUser.getId(), LocalDateTime.now());
                return ResponseModel.OK(loginUser.getId());
            } else {
                User user = new User();
                user.setOpenid(login.getOpenid())
                        .setSessionKey(login.getSession_key())
                        .setAvatarUrl(loginQO.getAvatarUrl())
                        .setNickname(loginQO.getNickName())
                        .setGender(loginQO.getGender())
                        .setCountry(loginQO.getCountry())
                        .setProvince(loginQO.getProvince())
                        .setCity(loginQO.getCity());
                userDao.insert(user);
                userDao.updateLastLoginTimeById(user.getId(), LocalDateTime.now());
                return ResponseModel.OK(user.getId());
            }
        }
        return ResponseModel.FAILED();
    }

    // 判断用户是否登录过
//    public ResponseModel hasLogin(String code) throws IOException {
//        Login login = WeChatUtil.httpsRequest(code);
//        User user = userDao.queryUserByOpenid(login.getOpenid());
//        if (user != null) {
//            if (!user.getStatus()) {
//                return ResponseModel.BAD_REQUEST("该用户被禁用");
//            }
//            return ResponseModel.OK(user);
//        }
//        return ResponseModel.UNAUTHORIZED();
//    }
}
