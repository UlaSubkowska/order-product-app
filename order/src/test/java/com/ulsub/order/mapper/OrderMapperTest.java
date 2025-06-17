package com.ulsub.order.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ulsub.order.Prototype;
import com.ulsub.order.PrototypeImpl;
import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.entity.PurchaseOrder;
import com.ulsub.order.entity.PurchaseOrderItem;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    private final Prototype prototype = new PrototypeImpl();

    @Test
    void testMapOrderDtoToEntity() {
        PurchaseOrderDto purchaseOrderDto = prototype.createPurchaseOrderDto();

        PurchaseOrder purchaseOrder = orderMapper.mapOrderDtoToEntity(purchaseOrderDto);

        assertNotNull(purchaseOrder);
        purchaseOrder.getPurchaseOrderItems().forEach(item -> {
            assertEquals(purchaseOrder, item.getPurchaseOrder());
            BigDecimal expectedTotalPrice = item.getPriceItem().multiply(item.getQuantity());
            assertEquals(expectedTotalPrice, item.getTotalPrice());
        });
        BigDecimal expectedTotalAmount = purchaseOrder.getPurchaseOrderItems().stream()
                .map(PurchaseOrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        assertEquals(expectedTotalAmount, purchaseOrder.getTotalAmount());
    }
}
