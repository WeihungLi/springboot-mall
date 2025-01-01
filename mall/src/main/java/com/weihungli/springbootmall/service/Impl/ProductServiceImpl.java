package com.weihungli.springbootmall.service.Impl;

import com.weihungli.springbootmall.dao.ProductDao;
import com.weihungli.springbootmall.dto.ProductQueryParams;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public List<Product> getProducts(ProductQueryParams params) {
        return productDao.getProducts(params);
    }

    @Override
    public Integer getTotal(ProductQueryParams params) {
        return productDao.getTotal(params);
    }

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

    @Override
    public Void deleteProduct(Integer id) {
        productDao.deleteProduct(id);
        return null;
    }
}
