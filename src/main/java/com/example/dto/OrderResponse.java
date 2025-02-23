package com.example.dto;

import com.example.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderResponse {
    private UUID orderId;
    private OrderStatus status;
}
