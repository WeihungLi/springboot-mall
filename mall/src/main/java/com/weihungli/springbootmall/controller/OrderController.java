package com.weihungli.springbootmall.controller;


import com.weihungli.springbootmall.dto.OrderCreateRequest;
import com.weihungli.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid OrderCreateRequest orderCreateRequest){

        Integer orderId = orderService.createOrder(userId,orderCreateRequest);


        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }




}
