package com.ulsub.product.dto;

import java.time.ZonedDateTime;

public record ErrorResponseDto(String errorMessage, ZonedDateTime timestamp) {

    public ErrorResponseDto(String errorMessage) {
        this(errorMessage, ZonedDateTime.now());
    }
}
