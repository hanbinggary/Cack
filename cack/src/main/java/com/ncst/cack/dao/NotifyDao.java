package com.ncst.cack.dao;

import com.ncst.cack.dto.NotifyDTO;
import com.ncst.cack.model.Notify;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@Repository
public interface NotifyDao {
    List<NotifyDTO> listNotify(int orderByGmtCreate);

    void insert(Notify notify);

    void delete(Long id);
}
