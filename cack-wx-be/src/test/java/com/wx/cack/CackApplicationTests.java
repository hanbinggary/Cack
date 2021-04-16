package com.wx.cack;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class CackApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("LocalDateTime.now().getHour() = " + LocalDateTime.now().getHour());

    }


}
