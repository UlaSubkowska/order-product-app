package com.ulsub.order.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderItemDto;
import com.ulsub.order.entity.PurchaseOrder;
import com.ulsub.order.entity.PurchaseOrderItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class OrderMapperTest {
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        orderMapper = Mappers.getMapper(OrderMapper.class);
    }

    @Test
    void testMapOrderDtoToEntity() {
        PurchaseOrderDto purchaseOrderDto = createPurchaseOrderDto();

        PurchaseOrder purchaseOrder = orderMapper.mapOrderDtoToEntity(purchaseOrderDto);

        assertNotNull(purchaseOrder);
        purchaseOrder.getPurchaseOrderItems().forEach(item -> {
            assertEquals(purchaseOrder, item.getPurchaseOrder());
            BigDecimal expectedTotalPrice = item.getPriceItem().multiply(item.getQuantity());
            assertEquals(0, expectedTotalPrice.compareTo(item.getTotalPrice()));
        });
        BigDecimal expectedTotalAmount = purchaseOrder.getPurchaseOrderItems().stream()
                .map(PurchaseOrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        assertEquals(0, expectedTotalAmount.compareTo(purchaseOrder.getTotalAmount()));
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

    private PurchaseOrderDto createPurchaseOrderDto() {
        return new PurchaseOrderDto(
                1L, 1L, BigDecimal.valueOf(2369.97).setScale(2, RoundingMode.HALF_UP), createPurchaseOrderItems());
    }
}
