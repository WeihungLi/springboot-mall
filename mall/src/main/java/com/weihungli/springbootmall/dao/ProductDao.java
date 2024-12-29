package com.weihungli.springbootmall.dao;

import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);
}
