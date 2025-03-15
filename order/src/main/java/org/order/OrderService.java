package org.order;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.order.dto.PurchaseOrderDto;
import org.order.entity.PurchaseOrder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    Long addOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.save(orderMapper.mapOrderDtoToEntity(purchaseOrderDto));
        return purchaseOrder.getPurchaseOrderId();
    }

    @Transactional
    List<PurchaseOrderDto> findAll() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream().map(orderMapper::mapOrderEntityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
