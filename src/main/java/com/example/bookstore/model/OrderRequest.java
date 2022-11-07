package com.example.bookstore.model;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class OrderRequest {
    @NotNull
    private Customer customer;
    @NotNull
    private Map<String, Integer> books; // bookId - quantity

}
