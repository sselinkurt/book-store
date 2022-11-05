package com.example.bookstore.service.impl;

import com.example.bookstore.model.Order;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }


    @Override
    public void add(Order order) {
        repository.save(order);
    }

    @Override
    public List<Order> getCustomersOrders() {
        return repository.findAll();
    }

}
