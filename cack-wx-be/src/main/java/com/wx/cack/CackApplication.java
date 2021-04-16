package com.wx.cack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wx.cack.dao")
@SpringBootApplication
public class CackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CackApplication.class, args);
    }

}
