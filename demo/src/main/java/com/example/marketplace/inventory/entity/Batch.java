package com.example.marketplace.inventory.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "batches")
@NoArgsConstructor()
@AllArgsConstructor()
public class Batch {
    @Id
    @Column(nullable = false, name = "id")
    private UUID id;

    @Column(nullable = false, name="item_id")
    private UUID itemId;

    @Column(nullable = false, name = "batch_number", unique = true)
    private String batchNumber;

    @Column(nullable = false, name = "unit_price")
    private Long unitPrice;

    @Column(nullable = false, name = "quantity")
    private Integer quantity;

    @Column(nullable = false, name = "reserved_quantity")
    private Integer reservedQuantity;

    @Column(nullable = true, name = "expiry_date")
    private LocalDate expiryDate;

    @Column(nullable = true, name = "manufacturer")
    private String manufacturer;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;
}
