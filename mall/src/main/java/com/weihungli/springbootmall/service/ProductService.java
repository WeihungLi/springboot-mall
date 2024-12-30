package com.weihungli.springbootmall.service;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;



public interface ProductService {

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    Void updateProduct(Integer id, ProductRequest productRequest);
}
