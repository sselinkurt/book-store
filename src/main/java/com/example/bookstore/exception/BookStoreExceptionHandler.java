package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class BookStoreExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ResponseEntity(methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }
}
