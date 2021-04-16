package com.wx.cack.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
@Accessors(chain = true)
public class Paging<T> {
    private List<T> list;
    private long total;
    private int pages;

    public static <T> Paging setPaging(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new Paging()
                .setList(list)
                .setTotal(pageInfo.getTotal())
                .setPages(pageInfo.getPages());
    }
}
