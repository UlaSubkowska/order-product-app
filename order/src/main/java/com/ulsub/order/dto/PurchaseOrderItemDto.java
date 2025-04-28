package com.ulsub.order.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PurchaseOrderItemDto(
        @NotNull
        Long productId,
        @NotNull
        @Digits(integer = 10, fraction = 2)
        BigDecimal quantity,
        @Digits(integer = 10, fraction = 2)
        BigDecimal priceItem,
        @Digits(integer = 10, fraction = 2)
        BigDecimal totalPrice) {}
