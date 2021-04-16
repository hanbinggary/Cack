package com.ncst.cack.controller;

import com.ncst.cack.qo.AdminUpdateQO;
import com.ncst.cack.dto.AdminDTO;
import com.ncst.cack.model.Admin;
import com.ncst.cack.service.AdminService;
import com.ncst.cack.util.Commons;
import com.ncst.cack.util.Paging;
import com.ncst.cack.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
    @Autowired
    AdminService adminService;


    @Secured("ADMIN")
    @GetMapping
    public ResponseModel adminList(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        List<AdminDTO> admins = adminService.listAdmin(pageNum, pageSize);
        return ResponseModel.OK(Paging.setPaging(admins));
    }

    @GetMapping("/{id}")
    public ResponseModel adminOne(@PathVariable Long id) {
        return ResponseModel.OK(adminService.getAdminById(id));
    }

    @GetMapping("/count/username")
    public ResponseModel adminCountByUsername(String username) {
        return ResponseModel.OK(adminService.countAdminByUsername(username));
    }


    @Secured("ADMIN")
    @PostMapping
    public ResponseModel adminInsert(@Valid @RequestBody Admin admin) {
        return ResponseModel.OK(adminService.insert(admin));
    }

    @PutMapping("/{id}/basic")
    public ResponseModel adminUpdate(@PathVariable Long id, @Validated AdminUpdateQO admin) {
        adminService.update(id, admin);
        return ResponseModel.OK();
    }

    @PutMapping("/{id}/reset/password")
    public ResponseModel adminResetPassword(@PathVariable Long id) {
        adminService.resetPassword(id);
        return ResponseModel.OK();
    }

    @Secured("ADMIN")
    @PutMapping("/{id}/role")
    public ResponseModel adminUpdateRoleId(@PathVariable Long id, Long roleId) {
        adminService.updateRoleIdById(id, roleId);
        return ResponseModel.OK();
    }

    @Secured("ADMIN")
    @PutMapping("/{id}/status")
    public ResponseModel adminUpdateStatusById(@PathVariable Long id,
                                               @NotNull(message = "状态不能为空") Boolean status) {
        adminService.updateStatusById(id, status);
        return ResponseModel.OK();
    }

    @PutMapping("/{id}/password")
    public ResponseModel adminUpdatePasswordById(@PathVariable Long id,
                                                 @NotBlank(message = "原密码不能为空") String rawPassword,
                                                 @NotBlank(message = "新密码不能为空") String newPassword) {

        if (adminService.updatePasswordById(id, rawPassword, newPassword)) {
            return ResponseModel.OK(true);
        } else {
            return ResponseModel.BAD_REQUEST("原密码输入错误");
        }
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseModel adminDelete(@PathVariable Long id) {
        adminService.delete(id);
        return ResponseModel.OK();
    }
}
