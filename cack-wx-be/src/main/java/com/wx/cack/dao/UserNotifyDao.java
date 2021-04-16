package com.wx.cack.dao;

import com.wx.cack.dto.UserNotifyDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/15
 */
@Repository
public interface UserNotifyDao {

    List<UserNotifyDTO> queryUnReadNotifyByUserId(Long userId);

    List<UserNotifyDTO> queryReadedNotifyByUserId(Long userId);

    UserNotifyDTO one(Long id);

    int countUnReadNotifyByUserId(Long userId);

    void insert(Long userId, Long notifyId);

    void updateReadedById(Long id, LocalDateTime readedTime);
}
