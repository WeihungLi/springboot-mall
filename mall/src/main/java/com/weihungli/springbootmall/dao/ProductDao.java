package com.weihungli.springbootmall.dao;

import com.weihungli.springbootmall.dto.ProductQueryParams;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {


    List<Product> getProducts(ProductQueryParams params);

    Integer getTotal(ProductQueryParams params);

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    Void updateProduct(Integer Id,ProductRequest productRequest);

    Void deleteProduct(Integer Id);

    Void updateStock(Integer productId,Integer stock);
}
