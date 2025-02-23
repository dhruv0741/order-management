package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    private Long userId;
    
    @ElementCollection
    private List<Long> itemIds;
    
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}