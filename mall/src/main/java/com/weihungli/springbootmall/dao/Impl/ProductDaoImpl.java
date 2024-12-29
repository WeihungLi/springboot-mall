package com.weihungli.springbootmall.dao.Impl;

import com.weihungli.springbootmall.dao.ProductDao;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "select product_id,product_name, category, image_url, price, stock, description, created_date, last_modified_date "+
                "from product where product_id = :productId";
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList != null && productList.size() > 0) {
            return productList.get(0);
        }
        else{
            return null;
        }
    }
}