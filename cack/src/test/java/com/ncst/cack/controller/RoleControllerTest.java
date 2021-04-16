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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author liuxuelian
 * @date 2021/04/01
 */
@SpringBootTest
@WebAppConfiguration
public class RoleControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RoleController()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void roleListTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/role")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ResponseModel model = Common.readValueToObject(result);
        System.out.println("model = " + model);
        System.out.println("result = " + model.getResult());
    }

    @Test
    void roleUpdateDescById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/role/1/desc")
                .param("desc", "所有权限")
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        ResponseModel model = Common.readValueToObject(result);
        System.out.println("model = " + model);
    }

    @Test
    void roleUpdateStatusById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/role/1/status")
                .param("status", "0")
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        ResponseModel model = Common.readValueToObject(result);
        System.out.println("model = " + model);
    }
}
