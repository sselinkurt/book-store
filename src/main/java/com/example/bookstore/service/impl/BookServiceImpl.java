package com.example.bookstore.service.impl;

import com.example.bookstore.exception.BadRequestException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void add(Book book) throws BadRequestException {
        applyBookValidation(book);
        bookRepository.save(book);
    }

    @Override
    public void updateStockCount(UUID bookId, int newStockCount) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) {
            throw new Exception();
        }
        Book dbBook = book.get();
        dbBook.setQuantity(newStockCount);
        bookRepository.save(dbBook);
    }

    public void applyBookValidation(Book book) throws BadRequestException {
        if (book.getPrice() < 0.0) {
            throw new BadRequestException("Book price must be greater than 0!");
        } else if (book.getQuantity() < 0) {
            throw new BadRequestException("Quantity of the book must be either 0 or greater than 0!");
        }
    }
}
