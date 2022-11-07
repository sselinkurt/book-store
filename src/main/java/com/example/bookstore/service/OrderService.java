package com.example.bookstore.service;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderRequest;

import java.util.UUID;

public interface OrderService {

    void addNewOrder(OrderRequest orderRequest) throws Exception;

    Order getOrderById(UUID orderId) throws Exception;

}
