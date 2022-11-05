package com.example.bookstore.service;

import com.example.bookstore.model.Book;

import java.util.List;

public interface BookService {

    void add(Book book);

    List<Book> getBooks();
}
