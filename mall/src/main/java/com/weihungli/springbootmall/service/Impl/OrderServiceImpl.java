package com.weihungli.springbootmall.service.Impl;

import com.weihungli.springbootmall.dao.OrderDao;
import com.weihungli.springbootmall.dao.ProductDao;
import com.weihungli.springbootmall.dto.BuyItems;
import com.weihungli.springbootmall.dto.OrderCreateRequest;
import com.weihungli.springbootmall.model.OrderItem;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;


    @Transactional
    @Override
    public Integer createOrder(Integer userId, OrderCreateRequest orderCreateRequest) {

        //計算總價錢
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItems buyItems: orderCreateRequest.getBuyItemsList()){
            Product product = productDao.getProductById(buyItems.getProductId());

            int amount = buyItems.getQuantity() * product.getProductPrice();
            totalAmount = totalAmount + amount;

            //轉換BuyItem成為OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(buyItems.getQuantity());
            orderItem.setProductId(buyItems.getProductId());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);


        orderDao.createOrderItem(orderId,orderItemList);


        return orderId;
    }
}
