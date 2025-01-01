package com.weihungli.springbootmall.service;

import com.weihungli.springbootmall.dto.OrderCreateRequest;

public interface OrderService {

    Integer createOrder(Integer userId, OrderCreateRequest orderCreateRequest);
}
