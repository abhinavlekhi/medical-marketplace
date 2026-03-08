package com.example.marketplace.inventory.repository;

import com.example.marketplace.inventory.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findByItemNameIgnoreCase(String itemName);
}
