package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.example"})
public class BookStoreApplication {

    @GetMapping("/message")
    public String getMessage() {
        return "Welcome to dockerized book store application..";
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

}
