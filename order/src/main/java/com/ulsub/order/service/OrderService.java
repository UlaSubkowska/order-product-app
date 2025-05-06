package com.ulsub.order.service;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderIdDto;
import com.ulsub.order.entity.PurchaseOrder;
import com.ulsub.order.exception.EntityNotFoundException;
import com.ulsub.order.mapper.OrderMapper;
import com.ulsub.order.repository.PurchaseOrderRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public PurchaseOrderIdDto addOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.save(orderMapper.mapOrderDtoToEntity(purchaseOrderDto));
        return new PurchaseOrderIdDto(purchaseOrder.getPurchaseOrderId());
    }

    @Transactional(readOnly = true)
    public List<PurchaseOrderDto> findAllOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream().map(orderMapper::mapOrderEntityToDto).toList();
    }

    @Transactional
    public void deleteOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        if (purchaseOrder.isEmpty()) {
            throw new EntityNotFoundException(String.format("Purchase order with id %s not found", id));
        }
        purchaseOrderRepository.delete(purchaseOrder.get());
    }
}
