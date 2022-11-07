package com.example.bookstore.repository;

import com.example.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query(value = "SELECT * FROM orders o WHERE o.customer_id = ?1", nativeQuery = true)
    List<Order> findOrdersByCustomerId(UUID customerId);
}
