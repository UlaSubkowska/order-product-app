package com.ulsub.order.dto;

import java.math.BigDecimal;
import java.util.List;

public record PurchaseOrderDto(Long clientId, BigDecimal totalAmount, List<PurchaseOrderItemDto> purchaseOrderItems) {}
