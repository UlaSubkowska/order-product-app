package com.ulsub.order;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderItemDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PrototypeClient {

    private Prototype purchaseOrderDtoPrototype;

    public PrototypeClient(Prototype purchaseOrderDtoPrototype) {
        this.purchaseOrderDtoPrototype = purchaseOrderDtoPrototype;
    }

    public PurchaseOrderDto createPurchaseOrderDto() {
        return purchaseOrderDtoPrototype.clone();
    }


    private List<PurchaseOrderItemDto> createPurchaseOrderItems() {
        return List.of(
                new PurchaseOrderItemDto(
                        1L,
                        BigDecimal.valueOf(2).setScale(1, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(624.99).setScale(2, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(1249.98).setScale(2, RoundingMode.HALF_UP)),
                new PurchaseOrderItemDto(
                        2L,
                        BigDecimal.valueOf(1).setScale(1, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(1119.99).setScale(2, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(1119.99).setScale(2, RoundingMode.HALF_UP)));
    }

    private PurchaseOrderDto createBasePurchaseOrderDto() {
        return new PurchaseOrderDto(
                1L, 1L, BigDecimal.valueOf(2369.97).setScale(2, RoundingMode.HALF_UP), createPurchaseOrderItems());
    }
}
