package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends ResponseStatusException {
    public BadRequestException(@Nullable String text) {
        super(HttpStatus.BAD_REQUEST, text);
    }
}
