package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") // users table for secure endpoints
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private String email;
}
