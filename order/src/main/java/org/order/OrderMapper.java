package org.order;

import org.mapstruct.Mapper;
import org.order.dto.PurchaseOrderDto;
import org.order.entity.PurchaseOrder;

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
