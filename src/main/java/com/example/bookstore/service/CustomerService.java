package com.example.bookstore.service;

import com.example.bookstore.model.Customer;

import java.util.List;

public interface CustomerService {

    void add(Customer customer);

    List<Customer> getAll();
}
