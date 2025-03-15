package org.order.dto;

public record PurchaseOrderItemDto(Long productId, double quantity, double priceItem, double totalPrice) {}
