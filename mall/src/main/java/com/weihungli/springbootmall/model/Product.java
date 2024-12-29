package com.weihungli.springbootmall.model;

import com.weihungli.springbootmall.constant.ProductCategory;

import java.util.Date;

public class Product {

    private int productId;
    private String productName;
    private ProductCategory productCategory;
    private String productImgUrl;
    private int productPrice;
    private int productStock;
    private String productDesc;
    private Date productCreateTime;
    private Date productModifiedTime;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public Date getProductModifiedTime() {
        return productModifiedTime;
    }

    public void setProductModifiedTime(Date productModifiedTime) {
        this.productModifiedTime = productModifiedTime;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }
}
