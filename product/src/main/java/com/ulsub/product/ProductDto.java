package com.ulsub.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record ProductDto(@Null(message = "Id must be null.") Long id, @NotBlank @Size(max = 100) String name) {}
