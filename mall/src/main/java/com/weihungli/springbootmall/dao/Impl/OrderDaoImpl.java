package com.weihungli.springbootmall.dao.Impl;

import com.weihungli.springbootmall.dao.OrderDao;
import com.weihungli.springbootmall.dto.OrderQueryParams;
import com.weihungli.springbootmall.dto.ProductQueryParams;
import com.weihungli.springbootmall.model.Order;
import com.weihungli.springbootmall.model.OrderItem;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.rowmapper.OrderItemRowMapper;
import com.weihungli.springbootmall.rowmapper.OrderRowMapper;
import com.weihungli.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `Order`(user_id,total_amount,created_date,last_modified_date)"+
                "VALUES (:userId, :totalAmount,:createdDate,:lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        Integer orderId = keyHolder.getKey().intValue();


        return orderId;
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        //一筆一筆更新數據
//        for (OrderItem orderItem : orderItemList) {
//            String sql = "INSERT INTO order_item(order_id,product_id,quantity,amount)"+
//                    "VALUES(:orderId,:productId,:quanyity,:amount)";
//            Map<String, Object> map = new HashMap<>();
//            map.put("orderId", orderId);
//            map.put("productId", orderItem.getProductId());
//            map.put("quantity", orderItem.getQuantity());
//            map.put("amount", orderItem.getAmount());
//
//            namedParameterJdbcTemplate.update(sql,map);
//        }
        //批次更新數據(效率高)
        String sql = "INSERT INTO order_item(order_id,product_id,quantity,amount)"+
                "VALUES(:orderId,:productId,:quantity,:amount)";
        MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[orderItemList.size()];

        for(int i=0;i<orderItemList.size();i++){
            OrderItem orderItem = orderItemList.get(i);
            mapSqlParameterSource[i] = new MapSqlParameterSource();
            mapSqlParameterSource[i].addValue("orderId", orderId);
            mapSqlParameterSource[i].addValue("productId", orderItem.getProductId());
            mapSqlParameterSource[i].addValue("quantity", orderItem.getQuantity());
            mapSqlParameterSource[i].addValue("amount", orderItem.getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSource);

    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id,user_id,total_amount,created_date,last_modified_date "+
                "FROM `order` WHERE order_id=:orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        if(orderList.size()>0){
            return orderList.get(0);
        }
        return null;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id,oi.order_id,oi.product_id,oi.quantity,oi.amount,p.product_name,p.image_url"+
                " FROM order_item as oi"+
                " LEFT JOIN product as p ON oi.product_id = p.product_id"+
                " WHERE oi.order_id=:orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList= namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());


        return orderItemList;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT order_id,user_id,total_amount,created_date,last_modified_date FROM `order` WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        sql = addFiliteringSql(sql,map,orderQueryParams);

        //排序
        sql = sql +" Order by created_date DESC";
        //分頁
        sql = sql+" LIMIT :limit OFFSET :offset";
        map.put("limit",orderQueryParams.getLimit());
        map.put("offset",orderQueryParams.getOffset());


        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        sql = addFiliteringSql(sql,map,orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return total;
    }


    private String addFiliteringSql(String sql, Map<String,Object> map, OrderQueryParams params) {


        if (params.getUserId() != null) {
            sql = sql +" AND user_id = :userId";
            map.put("userId", params.getUserId());
        }

        return sql;
    }
}
