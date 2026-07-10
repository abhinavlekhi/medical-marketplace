package com.example.marketplace.inventory.controller;

import com.example.marketplace.inventory.dto.CreateItemRequest;
import com.example.marketplace.inventory.dto.ItemResponse;
import com.example.marketplace.inventory.entity.Batch;
import com.example.marketplace.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<ItemResponse>> getFilteredItemsBasedOnPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(inventoryService.getItems(page, size));
    }
}
