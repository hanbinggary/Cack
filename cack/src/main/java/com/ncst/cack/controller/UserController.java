package com.ncst.cack.controller;

import com.ncst.cack.dto.UserDTO;
import com.ncst.cack.qo.UserQO;
import com.ncst.cack.service.UserService;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseModel userList(UserQO userQO) {
        List<UserDTO> users = userService.listUser(userQO);
        return ResponseModel.OK(Paging.setPaging(users));
    }

    @GetMapping("/{id}")
    public ResponseModel userOne(@PathVariable Long id) {
        return ResponseModel.OK(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseModel userUpdateStatus(@PathVariable Long id,
                                          @NotNull(message = "状态不能为空") Boolean status) {
        userService.updateStatusById(id, status);
        return ResponseModel.OK();
    }
}
