package org.order.dto;

import java.util.List;

public record PurchaseOrderDto(Long clientId, double totalAmount, List<PurchaseOrderItemDto> purchaseOrderItems) {}
