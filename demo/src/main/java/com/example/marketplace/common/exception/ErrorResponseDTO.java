package com.example.marketplace.common.exception;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

public class ErrorResponseDTO {
    @Getter
    private OffsetDateTime timestamp;
    @Getter
    private int status;
    @Getter
    private String error;

    private List<String> messages;
    @Getter
    private String path;

    public ErrorResponseDTO(int status, String error, List<String> messages, String path) {
        this.timestamp = OffsetDateTime.now();
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }
    public List<String> getMessages() {
        return messages;
    }
}
