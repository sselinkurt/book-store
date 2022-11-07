package com.example.bookstore.service.impl;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderDetail;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.StaticsService;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StaticsServiceImpl implements StaticsService {
    private final OrderRepository orderRepository;

    public StaticsServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Map<String, Map<String, Object>> getStaticsReport() {
        Map<String, Map<String, Object>> statics = new HashMap<>();

        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return statics;
        }
        Map<Integer, List<Order>> ordersByMonth = orders.stream().collect(Collectors.groupingBy(Order::getOrderMonth));
        for(Map.Entry<Integer, List<Order>> entry: ordersByMonth.entrySet()) {
            Map<String, Object> values = new HashMap<>();
            values.put("Total Order Count", entry.getValue().size());

            int totalBookCount = 0;
            for (Order order: entry.getValue()) {
                totalBookCount += order.getOrderDetails().stream().mapToInt(OrderDetail::getQuantity).sum();
            }
            values.put("Total Book Count", totalBookCount);

            double totalPrice = 0.0;
            for (Order order: entry.getValue()) {
                for (OrderDetail orderDetail: order.getOrderDetails()) {
                    totalPrice += orderDetail.getQuantity() * orderDetail.getBook().getPrice();
                }
            }
            values.put("Total Purchased Amount", totalPrice);

            statics.put(new DateFormatSymbols().getMonths()[entry.getKey() -1], values);
        }
        return statics;
    }
}
