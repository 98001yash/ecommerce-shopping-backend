package com.company.ecommerce.Shopping_Application.service;

import com.company.ecommerce.Shopping_Application.entitiy.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String username);
    List<Order> getUserOrders(String username);
}
