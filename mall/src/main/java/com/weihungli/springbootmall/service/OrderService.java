package com.weihungli.springbootmall.service;

import com.weihungli.springbootmall.dto.OrderCreateRequest;
import com.weihungli.springbootmall.dto.OrderQueryParams;
import com.weihungli.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, OrderCreateRequest orderCreateRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);

}
