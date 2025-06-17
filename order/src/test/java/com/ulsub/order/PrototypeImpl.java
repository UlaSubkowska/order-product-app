package com.ulsub.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ulsub.order.dto.PurchaseOrderDto;
import jakarta.inject.Inject;

public class PrototypeImpl implements Prototype {

    @Inject
    ObjectMapper objectMapper;

    private PurchaseOrderDto purchaseOrderDto;

    public PrototypeImpl(PurchaseOrderDto purchaseOrderDto) {
        this.purchaseOrderDto = purchaseOrderDto;
    }

    @Override
    public PurchaseOrderDto clone() {
        try {
            String json = objectMapper.writeValueAsString(purchaseOrderDto);
            return objectMapper.readValue(json, PurchaseOrderDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
