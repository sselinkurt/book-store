package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bookId")
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id", columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID bookId;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "price")
    @Min(0)
    private double price;

    @Column(name = "author")
    @NotNull
    private String author;

    @Column(name = "quantity")
    @Min(0)
    private int quantity;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
}
