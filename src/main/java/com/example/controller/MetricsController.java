package com.example.controller;

import com.example.model.OrderStatus;
import com.example.model.Order;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {
    private final OrderRepository orderRepository;

    @GetMapping("/total-orders")
    public long getTotalOrders() {
        return orderRepository.count();
    }

    @GetMapping("/orders-by-status")
    public Map<OrderStatus, Long> getOrdersByStatus() {
        return orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        order -> order.getStatus(),
                        Collectors.counting()
                ));
    }
}
