package com.example.bookstore.controller;

import com.example.bookstore.exception.BadRequestException;
import com.example.bookstore.model.Customer;
import com.example.bookstore.model.Order;
import com.example.bookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity addNewCustomer(@RequestBody Customer customer) {
        try {
            customerService.add(customer);
            return new ResponseEntity("Customer successfully saved.", HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Customer request failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getCustomersOrders(@RequestParam String customerId) {
        try {
            List<Order> customers = customerService.getCustomersOrders(UUID.fromString(customerId));
            return new ResponseEntity(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Customers orders could not found!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
