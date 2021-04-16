package com.ncst.cack.dao;

import com.ncst.cack.dto.RoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/01
 */
@Repository
public interface RoleDao {
    List<RoleDTO> listRole();

    void updateDescById(Long id, String desc);
}
