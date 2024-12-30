package com.weihungli.springbootmall.dto;

import com.weihungli.springbootmall.constant.ProductCategory;

public class ProductQueueParams {
    private ProductCategory category;
    private  String search;

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
}
