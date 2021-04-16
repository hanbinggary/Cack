package com.ncst.cack;

import javafx.util.converter.LocalDateStringConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CackApplicationTests {

    @Test
    void contextLoads() {
        String s = "2021-04-06";
        String[] split = s.split("-");
        System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
        LocalDate of = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        System.out.println("of = " + of);
    }

}
