package com.ncst.cack.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncst.cack.util.ResponseModel;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * not authorization 401
 * @author liuxuelian
 * @date 2021/03/24
 */
@Component
public class InvalidAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(new ObjectMapper().writeValueAsString(ResponseModel.UNAUTHORIZED()));
        response.getWriter().flush();
    }
}
