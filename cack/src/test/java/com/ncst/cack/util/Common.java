package com.ncst.cack.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;


/**
 * @author liuxuelian
 * @date 2021/04/02
 */
public class Common {
    public static ResponseModel readValueToObject(MvcResult result) throws Exception {
        return  new ObjectMapper().readValue(result.getResponse().getContentAsString(), ResponseModel.class);
    }

    public static String encoder(String raw) {
//        return new BCryptPasswordEncoder().encode(raw);
        return raw;
    }
}
