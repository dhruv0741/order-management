package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<Long> itemIds;
    private Double totalAmount;
}
