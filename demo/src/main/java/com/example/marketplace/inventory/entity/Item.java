package com.example.marketplace.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "items")
@NoArgsConstructor()
@AllArgsConstructor()
public class Item {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, name = "name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(nullable=false, name = "default_unit")
    private String defaultUnit;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

}
