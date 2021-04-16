package com.ncst.cack.controller;

import com.ncst.cack.util.Common;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@SpringBootTest
@WebAppConfiguration
public class AdminControllerTest {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminController()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void adminInsertTest() throws Exception {
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders
                .post("/admin")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("username", "admin")
                .param("password", Common.encoder("123456"))
                .param("nickname", "海贼王");

        MvcResult result = mockMvc.perform(content).andReturn();

        ResponseModel model = Common.readValueToObject(result);
        System.out.println("model = " + model);
    }
}
