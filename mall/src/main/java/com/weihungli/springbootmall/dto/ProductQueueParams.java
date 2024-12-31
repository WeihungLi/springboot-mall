package com.weihungli.springbootmall.dto;

import com.weihungli.springbootmall.constant.ProductCategory;

public class ProductQueueParams {
    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
