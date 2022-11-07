package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderDetailId")
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @Column(name = "order_detail_id", columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID orderDetailId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"orderDetails", "handler", "hibernateLazyInitializatier"}, allowSetters = true)
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"orderDetails", "handler", "hibernateLazyInitializatier"}, allowSetters = true)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity")
    private int quantity;

}
