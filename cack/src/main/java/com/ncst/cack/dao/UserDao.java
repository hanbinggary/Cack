package com.ncst.cack.dao;

import com.ncst.cack.dto.UserDTO;
import com.ncst.cack.qo.UserQO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Repository
public interface UserDao {
    List<UserDTO> listUser(UserQO userQO);

    List<Long> listIds();

    List<Long> queryUsageUserIds();

    UserDTO one(Long id);

    void updateStatusById(Long id, Boolean status);
}
