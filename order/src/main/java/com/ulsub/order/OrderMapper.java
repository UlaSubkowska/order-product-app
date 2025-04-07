package com.ulsub.order;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.entity.PurchaseOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    abstract PurchaseOrder mapToPurchaseOrder(PurchaseOrderDto purchaseOrderDto);

    abstract PurchaseOrderDto mapOrderEntityToDto(PurchaseOrder purchaseOrder);

    public PurchaseOrder mapOrderDtoToEntity(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = mapToPurchaseOrder(purchaseOrderDto);
        purchaseOrder
                .getPurchaseOrderItems()
                .forEach(purchaseOrderItem -> purchaseOrderItem.setPurchaseOrder(purchaseOrder));
        return purchaseOrder;
    }
}
