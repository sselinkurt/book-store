package com.example.bookstore.service;

import com.example.bookstore.model.Order;

import java.util.List;

public interface OrderService {
    void add(Order order);

    List<Order> getCustomersOrders();
}
