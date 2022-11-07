package com.example.bookstore.service.impl;

import com.example.bookstore.exception.BadRequestException;
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

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository repository, OrderRepository orderRepository) {
        this.customerRepository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(Customer customer) throws BadRequestException{
        applyCustomerValidation(customer);
        customerRepository.save(customer);
    }

    @Override
    public List<Order> getCustomersOrders(UUID customerId) {
        List<Order> orders = orderRepository.findOrdersByCustomerId(customerId);
        return orders;
    }

    private void applyCustomerValidation(Customer customer) throws BadRequestException {
        if (customer.getEmail().isBlank()) {
            throw new BadRequestException("Email is mandatory to register!");
        }
        List<Customer> customers = customerRepository.findCustomersByEmail(customer.getEmail());
        if (!customers.isEmpty()) {
            throw new BadRequestException("Customer with this email already exists!");
        }
    }
}
