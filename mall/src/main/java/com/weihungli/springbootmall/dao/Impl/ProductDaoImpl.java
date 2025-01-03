package com.weihungli.springbootmall.dao.Impl;

import com.weihungli.springbootmall.dao.ProductDao;
import com.weihungli.springbootmall.dto.ProductQueryParams;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    public List<Product> getProducts(ProductQueryParams params) {
        String sql = "select product_id,product_name, category, image_url, price, stock, description, created_date, last_modified_date "+
                "from product WHERE 1=1";
        Map<String, Object> map = new HashMap<String, Object>();
        //查詢條件
        sql = addFiliteringSql(sql,map,params);
        //排序
        sql = sql +" Order by " + params.getOrderBy()+" "+params.getSort();
        //分頁
        sql = sql+" LIMIT :limit OFFSET :offset";
        map.put("limit",params.getLimit());
        map.put("offset",params.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());


        return productList;
    }

    @Override
    public Integer getTotal(ProductQueryParams params) {
        String sql = "select COUNT(product_id) from product WHERE 1=1";
        Map<String, Object> map = new HashMap<String, Object>();
        //查詢條件
        sql = addFiliteringSql(sql,map,params);


        return namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "Insert INTO product(product_name, category, image_url, price, stock, description,created_date, last_modified_date )"+
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :created_date, :last_modified_date)";
        Map<String,Object> map= new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getProductCategory().toString());
        map.put("imageUrl", productRequest.getProductImgUrl());
        map.put("price", productRequest.getProductPrice());
        map.put("stock", productRequest.getProductStock());
        map.put("description", productRequest.getProductDesc());

        Date date = new Date();
        map.put("created_date",date);
        map.put("last_modified_date",date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        Integer productId = keyHolder.getKey().intValue();

        return productId;
    }


    @Override
    public Void updateProduct(Integer Id, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName, category= :category"+
                        ",image_url= :imageUrl, price= :price, stock= :stock, description= :description,last_modified_date = :last_modified_date "+
                        "WHERE product_id = :productId";
        Map<String,Object> map= new HashMap<>();
        map.put("productId", Id);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getProductCategory().toString());
        map.put("imageUrl", productRequest.getProductImgUrl());
        map.put("price", productRequest.getProductPrice());
        map.put("stock", productRequest.getProductStock());
        map.put("description", productRequest.getProductDesc());

        map.put("last_modified_date",new Date());

        namedParameterJdbcTemplate.update(sql,map);
        return null;
    }

    @Override
    public Void deleteProduct(Integer Id) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String,Object> map= new HashMap<>();
        map.put("productId", Id);

        namedParameterJdbcTemplate.update(sql,map);
        return null;
    }

    @Override
    public Void updateStock(Integer productId, Integer stock) {
        String sql = "UPDATE product SET stock= :stock,last_modified_date = :last_modified_date "+
                "WHERE product_id = :productId";
        Map<String,Object> map= new HashMap<>();
        map.put("productId", productId);
        map.put("stock",stock);

        map.put("last_modified_date",new Date());

        namedParameterJdbcTemplate.update(sql,map);
        return null;
    }

    private String addFiliteringSql(String sql, Map<String,Object> map, ProductQueryParams params) {


        if (params.getCategory() != null) {
            sql = sql +" AND category = :productCategory";
            map.put("productCategory", params.getCategory().name());
        }
        if (params.getSearch() != null) {
            sql = sql +" AND  product_name LIKE :search";
            map.put("search", "%" + params.getSearch() + "%");
        }

        return sql;
    }


}
