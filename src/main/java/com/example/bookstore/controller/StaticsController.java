package com.example.bookstore.controller;

import com.example.bookstore.service.StaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statics")
public class StaticsController {

    @Autowired
    private StaticsService staticsService;


    @GetMapping
    public ResponseEntity getStatics() {
        try {
            return new ResponseEntity(staticsService.getStaticsReport(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Statics report not found!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
