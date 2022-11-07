package com.example.bookstore.service;

import com.example.bookstore.model.Customer;
import com.example.bookstore.model.Order;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    void add(Customer customer);

    List<Order> getCustomersOrders(UUID customerId);


}
