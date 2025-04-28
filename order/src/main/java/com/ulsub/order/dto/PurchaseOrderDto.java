package com.ulsub.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;
import java.util.List;

public record PurchaseOrderDto(
        @Null(message = "Id must be null.")
        Long purchaseOrderId,
        @NotNull
        Long clientId,
        @Digits(integer = 10, fraction = 2)
        BigDecimal totalAmount,
        @Valid
        @NotNull
        @NotEmpty
        List<PurchaseOrderItemDto> purchaseOrderItems) {
}
