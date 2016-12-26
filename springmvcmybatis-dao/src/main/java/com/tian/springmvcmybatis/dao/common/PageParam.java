package com.tian.springmvcmybatis.dao.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询时的一个条件封装
 * Created by Administrator on 2016/11/14 0014.
 */
public class PageParam<T> {
    /** 页码: 默认第1页*/
    private int pageNumber = 1;
    /** 每页记录数: 默认每页10条记录*/
    private int pageSize = 10;
    /** 总记录数*/
    private int totalNumber;
    /** 总页数*/
    private int totalPages;
    /** 分页查询时,条件以K,V存储*/
    private Map<String , Object> params = new HashMap();
    /** 结果集*/
    private List<T> result;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        operationTotalPages();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 根据总记录数和每页记录数,运算总页数
     * @return
     */
    private int operationTotalPages(){
        return this.totalNumber%this.pageSize == 0 ? this.totalNumber/this.pageSize : this.totalNumber/this.pageSize+1;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalNumber=" + totalNumber +
                ", totalPages=" + totalPages +
                ", params=" + params +
                ", result=" + result +
                '}';
    }
}
