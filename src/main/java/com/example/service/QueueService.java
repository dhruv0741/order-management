package com.example.service;

import com.example.model.Order;
import com.example.model.OrderStatus;
import com.example.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final OrderRepository orderRepository;
    private final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public void addToQueue(Order order) {
        orderQueue.offer(order);
        processOrdersAsync();
    }

    @Async
    public void processOrdersAsync() {
        while (!orderQueue.isEmpty()) {
            try {
                Order order = orderQueue.take();
                Thread.sleep(2000); // Simulate processing time
                order.setStatus(OrderStatus.COMPLETED);
                orderRepository.save(order);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}