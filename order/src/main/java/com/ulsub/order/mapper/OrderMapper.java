package com.ulsub.order.mapper;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.entity.PurchaseOrder;
import com.ulsub.order.entity.PurchaseOrderItem;
import java.math.BigDecimal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract PurchaseOrder mapToPurchaseOrder(PurchaseOrderDto purchaseOrderDto);

    public abstract PurchaseOrderDto mapOrderEntityToDto(PurchaseOrder purchaseOrder);

    public PurchaseOrder mapOrderDtoToEntity(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = mapToPurchaseOrder(purchaseOrderDto);
        purchaseOrder.getPurchaseOrderItems().forEach(purchaseOrderItem -> {
            purchaseOrderItem.setPurchaseOrder(purchaseOrder);
            purchaseOrderItem.setTotalPrice(purchaseOrderItem.getPriceItem().multiply(purchaseOrderItem.getQuantity()));
        });
        purchaseOrder.setTotalAmount(purchaseOrder.getPurchaseOrderItems().stream()
                .map(PurchaseOrderItem::getTotalPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add));
        return purchaseOrder;
    }
}
