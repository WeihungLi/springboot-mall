package com.weihungli.springbootmall.controller;


import com.weihungli.springbootmall.dto.OrderCreateRequest;
import com.weihungli.springbootmall.dto.OrderQueryParams;
import com.weihungli.springbootmall.model.Order;
import com.weihungli.springbootmall.service.OrderService;
import com.weihungli.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid OrderCreateRequest orderCreateRequest){

        Integer orderId = orderService.createOrder(userId,orderCreateRequest);


        Order order = orderService.getOrderById(orderId);


        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }



    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrder(@PathVariable Integer userId,
                                                @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
                                                @RequestParam(defaultValue = "0") @Min(0) Integer offset){

        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        List<Order> orderList = orderService.getOrders(orderQueryParams);

        Integer count = orderService.countOrder(orderQueryParams);

        Page<Order> page = new Page<>();
        page.setTotal(count);
        page.setLimit(limit);
        page.setOffset(offset);
        page.setResult(orderList);



        return ResponseEntity.status(HttpStatus.OK).body(page);
    }




}
