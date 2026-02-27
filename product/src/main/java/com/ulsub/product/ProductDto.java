package com.ulsub.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductDto(
        @Null(message = "Id must be null.") Long id,
        @NotBlank @Size(max = 100) String name,
        @NotNull BigDecimal unitPrice,
        @NotNull BigDecimal quantityTotal,
        BigDecimal quantityReserved) {}
