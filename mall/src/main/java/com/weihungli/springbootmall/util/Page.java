package com.weihungli.springbootmall.util;


import java.util.List;

public class Page <T>{

    private Integer limit;
    private Integer offset;
    private Integer total;
    private List<T> result;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> list) {
        this.result = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
