package com.ncst.cack.security;

import com.ncst.cack.dao.LoginDao;
import com.ncst.cack.model.LoginInfo;
import com.ncst.cack.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author liuxuelian
 * @date 2021/03/24
 */
@Component
public class JwtAccessAuthenticationFilter extends OncePerRequestFilter {

    private final String AUTHORIZATION = "Authorization";
    private final String HEADER = "Bearer ";


    @Autowired
    LoginDao loginDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION);
        if (!Objects.isNull(authorization) && authorization.startsWith(HEADER)) {
            String token = authorization.substring(HEADER.length());
                String username = Jwt.getUsernameFromToken(token);

                if (!Objects.isNull(username)
                        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    LoginInfo loginInfo = loginDao.queryByUsername(username);

                    if (Jwt.validateToken(token, loginInfo)) {
                        SecurityContext context = SecurityContextHolder.createEmptyContext();

                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(loginInfo, null, loginInfo.getAuthorities());
                        context.setAuthentication(authenticationToken);
                        SecurityContextHolder.setContext(context);
                    }
                }
        }
        chain.doFilter(request, response);
    }
}
