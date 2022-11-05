package com.example.bookstore.service.impl;

import com.example.bookstore.model.Customer;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Customer customer) {
        repository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }
}
