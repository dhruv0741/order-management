package com.example.service;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.model.Order;
import com.example.model.OrderStatus;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final QueueService queueService;

    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .userId(request.getUserId())
                .itemIds(request.getItemIds())
                .totalAmount(request.getTotalAmount())
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);
        queueService.addToQueue(savedOrder);

        return new OrderResponse(savedOrder.getOrderId(), savedOrder.getStatus());
    }

    public OrderResponse getOrderStatus(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(order -> new OrderResponse(order.getOrderId(), order.getStatus()))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}