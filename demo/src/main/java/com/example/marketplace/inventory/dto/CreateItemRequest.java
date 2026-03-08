package com.example.marketplace.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class CreateItemRequest {
    @NotBlank(message="itemName is required")
    private String itemName;

    @Positive(message="unitPrice should always be positive")
    private long unitPrice;

    @Positive(message="quantity should always be positive")
    private int quantity;

    @NotBlank(message="unit is required")
    private String unit;

    @NotBlank(message="batchNumber is required")
    private String batchNumber;

    @NotNull(message="expiryDate is required")
    private LocalDate expiryDate;

    private String manufacturer;
}
