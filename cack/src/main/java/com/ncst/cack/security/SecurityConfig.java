package com.ncst.cack.security;

import com.ncst.cack.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginService loginService;

    @Autowired
    JwtAccessAuthenticationFilter jwtAccessAuthenticationFilter;

    @Autowired
    InvalidAuthenticationEntryPoint invalidAuthenticationEntryPoint;

    @Autowired
    DefaultLogoutSuccessHandler defaultLogoutSuccessHandler;

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers("/admin/**", "/role/**").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
                .anyRequest().authenticated()

                .and() // login and access authentication
                .addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAccessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling() // custom deny access authentication
                .authenticationEntryPoint(invalidAuthenticationEntryPoint)

                .and() // logout
                .logout()
                .logoutSuccessHandler(defaultLogoutSuccessHandler)
                .clearAuthentication(true)
                .deleteCookies("token");
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception{
        return new LoginAuthenticationFilter("/login", authenticationManager());
    }

}
