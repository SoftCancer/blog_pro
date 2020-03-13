package com.dongl.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 封装分页返回数据类型
 * @author: YaoGuangXun
 * @date: 2020/3/13 22:21
 * @Version: 1.0
 */
@Data
public class PageResult<T> {

    private long total;

    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
