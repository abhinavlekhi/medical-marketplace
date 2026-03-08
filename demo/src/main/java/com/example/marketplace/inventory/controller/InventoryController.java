package com.example.marketplace.inventory.controller;

import com.example.marketplace.inventory.dto.CreateItemRequest;
import com.example.marketplace.inventory.entity.Batch;
import com.example.marketplace.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Batch> createItem(@Valid @RequestBody CreateItemRequest request) {
        Batch batch = inventoryService.createOrUpdateBatch(request);
        return ResponseEntity.ok(batch);
    }
}
