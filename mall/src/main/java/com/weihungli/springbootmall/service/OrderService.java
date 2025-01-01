package com.weihungli.springbootmall.service;

import com.weihungli.springbootmall.dto.OrderCreateRequest;
import com.weihungli.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, OrderCreateRequest orderCreateRequest);

    Order getOrderById(Integer orderId);
}
