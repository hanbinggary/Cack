package com.ncst.cack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncst.cack.util.Common;
import com.ncst.cack.util.ResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@SpringBootTest
@WebAppConfiguration
public class BookingTimeControllerTest {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new BookingTimeController()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void deleteBatchTest() throws Exception {

    }
}
