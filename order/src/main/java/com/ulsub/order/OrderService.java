package com.ulsub.order;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.entity.PurchaseOrder;
import java.util.List;
import java.util.NoSuchElementException;
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
    Long addOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.save(orderMapper.mapOrderDtoToEntity(purchaseOrderDto));
        return purchaseOrder.getPurchaseOrderId();
    }

    @Transactional(readOnly = true)
    List<PurchaseOrderDto> findAllOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream().map(orderMapper::mapOrderEntityToDto).toList();
    }

    @Transactional
    public void deleteOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        if (purchaseOrder.isEmpty()) {
            throw new NoSuchElementException("Purchase order not found");
        }
        purchaseOrderRepository.delete(purchaseOrder.get());
    }
}
