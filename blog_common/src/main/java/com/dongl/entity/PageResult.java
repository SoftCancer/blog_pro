package com.dongl.entity;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description: 封装分页返回数据类型
 * @author: YaoGuangXun
 * @date: 2020/3/13 22:21
 * @Version: 1.0
 */
@Data
public class PageResult<T> {

    /** 总页数 **/
    private long total;

    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult(Page<T> o) {
        this.total = o.getTotalElements();
        this.rows = o.getContent();
    }
}
