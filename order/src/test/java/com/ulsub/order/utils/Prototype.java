package com.ulsub.order.utils;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderItemDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Prototype {

    public PurchaseOrderDto createPurchaseOrderDto() {
        return new PurchaseOrderDto(
                1L, 1L, BigDecimal.valueOf(2369.97).setScale(2, RoundingMode.HALF_UP), createPurchaseOrderItems());
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

    public PurchaseOrderDto createPurchaseOrderDto_second() {
        return new PurchaseOrderDto(
                2L,
                3L,
                BigDecimal.valueOf(704.99).setScale(2, RoundingMode.HALF_UP),
                List.of(new PurchaseOrderItemDto(
                        3L,
                        BigDecimal.valueOf(1).setScale(1, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(704.99).setScale(2, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(704.99).setScale(2, RoundingMode.HALF_UP))));
    }
}
