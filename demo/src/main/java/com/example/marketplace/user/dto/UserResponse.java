package com.example.marketplace.user.dto;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

public class UserResponse {

    private UUID uuid;
    private Instant createdAt;
    private String username;

    public UserResponse(UUID uuid, Instant createdAt, String username) {
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.username = username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
