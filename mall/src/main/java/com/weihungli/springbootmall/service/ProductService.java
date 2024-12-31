package com.weihungli.springbootmall.service;
import com.weihungli.springbootmall.constant.ProductCategory;
import com.weihungli.springbootmall.dto.ProductQueueParams;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;

import java.util.List;


public interface ProductService {

    List<Product> getProducts(ProductQueueParams params);

    Integer getTotal(ProductQueueParams params);

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    Void updateProduct(Integer id, ProductRequest productRequest);

    Void deleteProduct(Integer id);
}
