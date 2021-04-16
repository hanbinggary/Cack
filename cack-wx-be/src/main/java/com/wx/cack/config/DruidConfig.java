package com.wx.cack.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author liuxuelian
 * @date 2021/04/01
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource()   {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean
                = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/**");

        bean.addInitParameter("allow", "127.0.0.1");
        bean.addInitParameter("deny", "");
        bean.addInitParameter("loginUsername", "user");
        bean.addInitParameter("loginPassword", "123");
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatViewFilter() {
        FilterRegistrationBean<WebStatFilter> bean
                = new FilterRegistrationBean<>(new WebStatFilter());

        bean.addInitParameter("urlPatterns", "/*");
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return bean;
    }
}
