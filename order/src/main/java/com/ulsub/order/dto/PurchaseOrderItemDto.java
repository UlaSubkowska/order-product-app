package com.ulsub.order.dto;

import java.math.BigDecimal;

public record PurchaseOrderItemDto(Long productId, BigDecimal quantity, BigDecimal priceItem, BigDecimal totalPrice) {}
