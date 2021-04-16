package com.ncst.cack.service;

import com.ncst.cack.dao.RoleDao;
import com.ncst.cack.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/01
 */
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public List<RoleDTO> listRole() {
        return roleDao.listRole();
    }

    public void updateDescById(Long id, String desc) {
        roleDao.updateDescById(id, desc);
    }

}
