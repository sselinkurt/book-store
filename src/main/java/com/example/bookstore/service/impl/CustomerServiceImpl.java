package com.example.bookstore.service.impl;

import com.example.bookstore.model.Customer;
import com.example.bookstore.model.Order;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(Customer customer) {
        repository.save(customer);
    }

    @Override
    public List<Order> getCustomersOrders(UUID customerId) {
        List<Order> orders = orderRepository.findOrdersByCustomerId(customerId);
        return orders;
    }


}
