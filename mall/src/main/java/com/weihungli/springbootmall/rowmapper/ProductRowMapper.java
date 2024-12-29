package com.weihungli.springbootmall.rowmapper;

import com.weihungli.springbootmall.constant.ProductCategory;
import com.weihungli.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));

        String categoryStr= rs.getString("category");
        ProductCategory category = ProductCategory.valueOf(categoryStr);
        product.setProductCategory(category);

        //product.setProductCategory(ProductCategory.valueOf(rs.getString("category")));

        product.setProductImgUrl(rs.getString("image_url"));
        product.setProductPrice(rs.getInt("price"));
        product.setProductStock(rs.getInt("stock"));
        product.setProductDesc(rs.getString("description"));
        product.setProductCreateTime(rs.getTimestamp("created_date"));
        product.setProductModifiedTime(rs.getTimestamp("last_modified_date"));

        return product;
    }
}
