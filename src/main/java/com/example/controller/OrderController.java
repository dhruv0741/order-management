package com.example.controller;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderStatus(@PathVariable UUID orderId) {
        return orderService.getOrderStatus(orderId);
    }
}
