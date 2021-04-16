package com.wx.cack.controller;

import com.wx.cack.model.User;
import com.wx.cack.qo.LoginQO;
import com.wx.cack.service.LoginService;
import com.wx.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author liuxuelian
 * @date 2021/04/12
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseModel login(@Valid @RequestBody LoginQO loginQO) throws IOException {
        return loginService.login(loginQO);
    }

//    @GetMapping("/has/login")
//    public ResponseModel hasLogin(String code) throws IOException {
//        return loginService.hasLogin(code);
//    }
}
