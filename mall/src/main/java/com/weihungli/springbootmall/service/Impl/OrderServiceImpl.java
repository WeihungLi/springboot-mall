package com.weihungli.springbootmall.service.Impl;

import com.weihungli.springbootmall.dao.OrderDao;
import com.weihungli.springbootmall.dao.ProductDao;
import com.weihungli.springbootmall.dao.UserDao;
import com.weihungli.springbootmall.dto.BuyItems;
import com.weihungli.springbootmall.dto.OrderCreateRequest;
import com.weihungli.springbootmall.model.Order;
import com.weihungli.springbootmall.model.OrderItem;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.model.User;
import com.weihungli.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Transactional
    @Override
    public Integer createOrder(Integer userId, OrderCreateRequest orderCreateRequest) {

        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("使用者:{} 未註冊",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //計算總價錢
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItems buyItems: orderCreateRequest.getBuyItemsList()){
            Product product = productDao.getProductById(buyItems.getProductId());

            if (product == null) {
                log.warn("商品:{} 不存在",buyItems.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            else if (product.getProductStock()<buyItems.getQuantity()){
                log.warn("商品:{} 庫存不足，尚餘：｛｝",product.getProductId(),product.getProductStock());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(product.getProductId(),product.getProductStock()-buyItems.getQuantity());


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


    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItems(orderItemList);
        return order;
    }
}
