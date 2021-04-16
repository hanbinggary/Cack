package com.ncst.cack.service;

import com.github.pagehelper.PageHelper;
import com.ncst.cack.qo.AdminUpdateQO;
import com.ncst.cack.dao.AdminDao;
import com.ncst.cack.dto.AdminDTO;
import com.ncst.cack.model.Admin;
import com.ncst.cack.util.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;

    public List<AdminDTO> listAdmin(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminDao.listAdmin();
    }

    public AdminDTO getAdminById(Long id) {
        return adminDao.one(id);
    }

    public int countAdminByUsername(String username) {
        return adminDao.countAdminByUsername(username);
    }

    public Long insert(Admin admin) {
        admin.setPasswordLevel(Commons.getPasswordLevel(admin.getPassword()));
        admin.setPassword(Commons.encoder(admin.getPassword()));
        adminDao.insert(admin);
        return admin.getId();
    }

    public void update(Long id, AdminUpdateQO admin) {
        if (admin.getNickname() != null || admin.getPhone() != null) {
            adminDao.update(id, admin);
        }
    }

    public void resetPassword(Long id) {
        String addSaltPassword = Commons.encoder("123456");
        adminDao.resetPassword(id, addSaltPassword);
    }

    public void updateRoleIdById(Long id, Long roleId) {
        adminDao.updateRoleIdById(id, roleId);
    }

    public void updateStatusById(Long id, Boolean status) {
        adminDao.updateStatusById(id, status);
    }

    @Transactional
    public Boolean updatePasswordById(Long id, String rawPassword, String newPassword) {
        // 原密码和数据库加密密码进行比对是否一致
        String encodedRawPassword = adminDao.queryPasswordById(id);
        if (Commons.matchesPassword(rawPassword, encodedRawPassword)) {
            adminDao.updatePasswordById(id, Commons.encoder(newPassword), Commons.getPasswordLevel(newPassword));
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        adminDao.delete(id);
    }
}
