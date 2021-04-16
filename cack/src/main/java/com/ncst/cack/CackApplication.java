package com.ncst.cack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.ncst.cack.dao")
@SpringBootApplication
public class CackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CackApplication.class, args);
    }

}
