package com.ncst.cack.controller;

import com.ncst.cack.service.RoleService;
import com.ncst.cack.util.ResponseModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author liuxuelian
 * @date 2021/04/01
 */
// ADMIN权限
@RestController
@RequestMapping("/role")
@Validated
@Secured("ADMIN")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseModel roleList() {
        return ResponseModel.OK(roleService.listRole());
    }

    @PutMapping("/{id}/desc")
    public ResponseModel roleUpdateDescById(@PathVariable Long id,
                                            @Length(max = 100, message = "描述必须小于100个字符") String desc) {
        roleService.updateDescById(id, desc);
        return ResponseModel.OK();
    }

}
