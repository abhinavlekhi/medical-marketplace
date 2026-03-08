package com.example.marketplace.inventory.service;

import com.example.marketplace.common.exception.BadRequestException;
import com.example.marketplace.inventory.dto.CreateItemRequest;
import com.example.marketplace.inventory.entity.Batch;
import com.example.marketplace.inventory.entity.Item;
import com.example.marketplace.inventory.repository.BatchRepository;
import com.example.marketplace.inventory.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final BatchRepository batchRepository;

    public InventoryService(ItemRepository itemRepository, BatchRepository batchRepository) {
        this.itemRepository = itemRepository;
        this.batchRepository = batchRepository;
    }

    @Transactional // Apart from ensuring atomicity of the operation,
    // @Transactional also ensures that operations are executed in a single thread,
    // which helps to prevent race conditions when multiple requests are,
    // trying to create/update batches for same item concurrently.
    public Batch createOrUpdateBatch(CreateItemRequest createItemRequest) {
        Item item = itemRepository.findByItemNameIgnoreCase(createItemRequest.
                        getItemName()).orElseGet(() -> {
                            Item newItem = new Item();
                            newItem.setId(UUID.randomUUID());
                            newItem.setItemName(createItemRequest.getItemName());
                            newItem.setDefaultUnit(createItemRequest.getUnit());
                            newItem.setCreatedAt(Instant.now());
                            return itemRepository.save(newItem);
                        });
        Optional<Batch> existingBatch=batchRepository.findByBatchNumber(createItemRequest.getBatchNumber());
//        Batch existingBatch = batchRepository.findByBatchNumber(createItemRequest.getBatchNumber()).orElse(null);
        if (existingBatch.isPresent()) {
            Batch batch = existingBatch.get();

            if (!batch.getUnitPrice().equals(createItemRequest.getUnitPrice())) {
                throw new BadRequestException("Unit price mismatch for existing batch");
            }

            batch.setQuantity(batch.getQuantity() + createItemRequest.getQuantity());
            return batchRepository.save(batch);
        }
        Batch batch = new Batch();
        batch.setId(UUID.randomUUID());
        batch.setItemId(item.getId());
        batch.setBatchNumber(createItemRequest.getBatchNumber());
        batch.setUnitPrice(createItemRequest.getUnitPrice());
        batch.setQuantity(createItemRequest.getQuantity());
        batch.setReservedQuantity(0);
        batch.setExpiryDate(createItemRequest.getExpiryDate());
        batch.setManufacturer(createItemRequest.getManufacturer());
        batch.setCreatedAt(Instant.now());

        return batchRepository.save(batch);
    }
}
