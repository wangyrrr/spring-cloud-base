package org.example.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页查询结果封装
 * @Author: wangyuanrong
 * @Date: 2023-04-08  10:34
 */
@Data
public class PageResult<T> implements Serializable {

    /**
     * 结果记录
     */
    private List<T> records;

    /**
     * 总条数
     */
    private long total;

    /**
     * 当前页
     */
    private long current;

    /**
     * 每页数量
     */
    private long size;

    public PageResult() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.current = 1L;
        this.size = 10L;
    }
}
