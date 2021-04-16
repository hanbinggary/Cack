package com.ncst.cack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 日期转换器配置
 * @author liuxuelian
 * @date 2021/04/06
 */
@Configuration
public class MappingConverterAdapter {
    /**
     * 日期 "yyyy-MM-dd" 转换成 LocalDate格式
     */
    @Bean
    public Converter<String, LocalDate> dateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String s) {
                String[] split = s.split("-");
                LocalDate of = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                return of;
            }
        };
    }
}
