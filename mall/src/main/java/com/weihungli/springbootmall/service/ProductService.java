package com.weihungli.springbootmall.service;
import com.weihungli.springbootmall.dto.ProductQueryParams;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;

import java.util.List;


public interface ProductService {

    List<Product> getProducts(ProductQueryParams params);

    Integer getTotal(ProductQueryParams params);

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    Void updateProduct(Integer id, ProductRequest productRequest);

    Void deleteProduct(Integer id);
}
