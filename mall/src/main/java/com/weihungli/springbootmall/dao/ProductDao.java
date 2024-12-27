package com.weihungli.springbootmall.dao;

import com.weihungli.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer id);
}
