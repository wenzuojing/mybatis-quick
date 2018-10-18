package com.github.wens.mybatis.support;

import com.github.wens.mybatis.plugin.pagination.Pagination;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author wens
 * @Date 2018-10-10
 */
public class Page<T> extends Pagination {

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();

    private Object  extra ;


    protected Page() {
        /* 保护 */
    }


    public Page(int current, int size) {
        super(current, size);
    }


    public List<T> getRecords() {
        return records;
    }


    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        StringBuffer pg = new StringBuffer();
        pg.append(" Page:{ [").append(super.toString()).append("], ");
        if (records != null) {
            pg.append("records-size:").append(records.size());
        } else {
            pg.append("records is null");
        }
        return pg.append(" }").toString();
    }
}
