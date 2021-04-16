package com.ncst.cack.dao;

import com.ncst.cack.qo.AdminUpdateQO;
import com.ncst.cack.dto.AdminDTO;
import com.ncst.cack.model.Admin;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Repository
public interface AdminDao {

    List<AdminDTO> listAdmin();

    AdminDTO one(Long id);

    String queryPasswordById(Long id);

    int countAdminByUsername(String username);

    void insert(Admin admin);

    void resetPassword(Long id, String newPassword);

    void update(Long id, AdminUpdateQO admin);

    void updateRoleIdById(Long id, Long roleId);

    void updateStatusById(Long id, Boolean status);

    void updateLastLoginTimeById(Long id, LocalDateTime lastLoginTime);

    void updatePasswordById(Long id, String password, int passwordLevel);

    void delete(Long id);
}
