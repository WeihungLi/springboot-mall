package com.weihungli.springbootmall.dao;

import com.weihungli.springbootmall.constant.ProductCategory;
import com.weihungli.springbootmall.dto.ProductQueueParams;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {


    List<Product> getProducts(ProductQueueParams params);

    Integer getTotal(ProductQueueParams params);

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    Void updateProduct(Integer Id,ProductRequest productRequest);

    Void deleteProduct(Integer Id);

    Void updateStock(Integer productId,Integer stock);
}
