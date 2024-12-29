package com.weihungli.springbootmall.dto;

import com.weihungli.springbootmall.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ProductRequest {


    @NotNull
    private String productName;
    @NotNull
    private ProductCategory productCategory;
    @NotNull
    private String productImgUrl;
    @NotNull
    private Integer productPrice;
    @NotNull
    private Integer productStock;
    private String productDesc;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
