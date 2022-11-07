package com.example.bookstore.service.impl;

import com.example.bookstore.model.OrderDetail;
import com.example.bookstore.repository.OrderDetailRepository;
import com.example.bookstore.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository repository;

    public OrderDetailServiceImpl(OrderDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(OrderDetail orderDetail) {
        repository.save(orderDetail);
    }


}
