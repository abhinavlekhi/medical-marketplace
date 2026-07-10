package com.example.marketplace.inventory.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ItemResponse {
    private UUID itemId;
    private String itemName;
    private String unit;
    private Long price;
    private Integer availableQuantity;
    private LocalDate expiryDate;

    public ItemResponse(UUID itemId, String itemName, String unit, Long price,
                        Integer availableQuantity, LocalDate expiryDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.unit = unit;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.expiryDate = expiryDate;
    }
}