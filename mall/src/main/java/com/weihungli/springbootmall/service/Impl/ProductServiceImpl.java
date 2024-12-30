package com.weihungli.springbootmall.service.Impl;

import com.weihungli.springbootmall.dao.ProductDao;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer id) {
        return productDao.getProductById(id);
    }


    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public Void updateProduct(Integer id, ProductRequest productRequest) {

        productDao.updateProduct(id,productRequest);

        return null;
    }
}
