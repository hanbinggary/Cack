package com.ncst.cack.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncst.cack.dao.LoginDao;
import com.ncst.cack.dto.LoginInfoDTO;
import com.ncst.cack.model.LoginInfo;
import com.ncst.cack.service.LoginService;
import com.ncst.cack.util.Constant;
import com.ncst.cack.util.Jwt;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sun.net.ResourceManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
public class LoginAuthenticationFilter
            extends AbstractAuthenticationProcessingFilter {

    @Autowired
    LoginDao loginDao;

    public LoginAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        LoginInfo loginInfo = new ObjectMapper().readValue(request.getInputStream(), LoginInfo.class);
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");

        LoginInfo loginInfo = (LoginInfo) authResult.getPrincipal();
        LoginInfoDTO loginInfoDTO = new LoginInfoDTO()
                .setId(loginInfo.getId())
                .setUsername(loginInfo.getUsername())
                .setNickname(loginInfo.getNickname())
                .setPasswordLevel(loginInfo.getPasswordLevel())
                .setRoleName(loginInfo.getRoleName());

        loginInfoDTO.setToken(Jwt.generateToken(loginInfo));

        // 更新最后一次登录时间
        loginDao.updateLastLoginTimeById(loginInfo.getId(), LocalDateTime.now());

        response.getWriter().println(new ObjectMapper().writeValueAsString(ResponseModel.OK(loginInfoDTO)));
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");

        String failedMessage = failed.getMessage();
        String responseMessage = Constant.USERNAME_OR_PASSWORD_ERROR.getMessage();

        if (Constant.USER_DISABLED.getEnglishMessage().equals(failedMessage)) {
            responseMessage = Constant.USER_DISABLED.getMessage();
        }

        response.getWriter().println(new ObjectMapper().writeValueAsString(ResponseModel.BAD_REQUEST(responseMessage)));
        response.getWriter().flush();
    }
}
