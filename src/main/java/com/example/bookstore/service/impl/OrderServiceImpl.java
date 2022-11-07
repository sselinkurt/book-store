package com.example.bookstore.service.impl;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderDetail;
import com.example.bookstore.model.OrderRequest;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderDetailRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void addNewOrder(OrderRequest orderRequest) throws Exception {
        try {
            stockCheck(orderRequest.getBooks());
            Order order = processOrderRequest(orderRequest);
            orderRepository.save(order);
            order.getOrderDetails().forEach(orderDetail -> orderDetailRepository.save(orderDetail));
            updateStock(orderRequest.getBooks());
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public Order getOrderById(UUID orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new Exception();
        }
        return order.get();
    }

    private Order processOrderRequest(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomer(orderRequest.getCustomer());
        order.setOrderTime(LocalDate.now());
        order.setOrderMonth(LocalDate.now().getMonthValue());

        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Map.Entry entry: orderRequest.getBooks().entrySet()) {
            Optional<Book> book = bookRepository.findById(UUID.fromString(entry.getKey().toString()));
            if (book.isPresent()) {
                OrderDetail orderDetail = new OrderDetail(null, order, book.get(), (Integer) entry.getValue());
                orderDetails.add(orderDetail);
            }
        }
        order.setOrderDetails(orderDetails);
        return order;
    }

    private void stockCheck(Map<String, Integer> books) throws Exception {
        for(Map.Entry entry: books.entrySet()) {
            Optional<Book> book = bookRepository.findById(UUID.fromString(entry.getKey().toString()));
            if (!book.isPresent()) {
                throw new Exception();
            }
            if ((Integer) entry.getValue() < 1 || book.get().getQuantity() < (Integer) entry.getValue()) {
                throw new Exception();
            }
        }
    }

    private void updateStock(Map<String, Integer> books) throws Exception {
        for(Map.Entry entry: books.entrySet()) {
            Optional<Book> book = bookRepository.findById(UUID.fromString(entry.getKey().toString()));
            if (!book.isPresent()) {
                throw new Exception();
            }
            book.get().setQuantity(book.get().getQuantity() - (Integer) entry.getValue());
            bookRepository.save(book.get());
        }
    }

}
