package com.example.bookstore;

import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.example"})
public class BookStoreApplication {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(new User(1, "selinkurt", "selinkurt", "seliin.kurttt@gmail.com"),
                new User(2, "guest", "123456", "guest@gmail.com")).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    @GetMapping("/message")
    public String getMessage() {
        return "Welcome to dockerized book store application..";
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

}
