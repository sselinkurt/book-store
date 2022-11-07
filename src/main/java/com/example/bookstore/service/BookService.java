package com.example.bookstore.service;

import com.example.bookstore.model.Book;

import java.util.UUID;

public interface BookService {

    void add(Book book);

    void updateStockCount(UUID uuid, int newStockCount) throws Exception;

}
