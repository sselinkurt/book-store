package com.example.bookstore.controller;

import com.example.bookstore.exception.BadRequestException;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity addNewBook(@RequestBody @NotNull Book book) {
        try {
            bookService.add(book);
            return new ResponseEntity("Book successfully saved.", HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Book request failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateStockCount(@RequestParam @NotNull UUID bookId,
                                              @RequestParam @Min(0) int newStockCount) {
        try {
            bookService.updateStockCount(bookId, newStockCount);
            return new ResponseEntity("Stock count updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Stock count could not updated!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
