package com.example.marketplace.common.exception;

import lombok.Getter;

import java.time.OffsetDateTime;

public class ErrorResponseDTO {
    @Getter
    private OffsetDateTime timestamp;
    @Getter
    private int status;
    @Getter
    private String error;
    @Getter
    private String message;
    @Getter
    private String path;

    public ErrorResponseDTO(int status, String error, String message, String path) {
        this.timestamp = OffsetDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
