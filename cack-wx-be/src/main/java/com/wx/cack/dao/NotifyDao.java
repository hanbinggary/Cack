package com.wx.cack.dao;

import com.wx.cack.model.Notify;
import org.springframework.stereotype.Repository;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Repository
public interface NotifyDao {

    void insert(Notify notify);
}
