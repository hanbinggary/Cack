package com.ncst.cack.dao;

import com.ncst.cack.dto.UserNotifyDTO;
import com.ncst.cack.model.UserNotify;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@Repository
public interface UserNotifyDao {
    List<UserNotifyDTO> listUserNotify(Long notifyId);

    void insertBatch(List<UserNotify> userNotifies);

    void delete(Long notifyId);
}
