package com.ulsub.order.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderIdDto;
import com.ulsub.order.entity.PurchaseOrder;
import com.ulsub.order.exception.EntityNotFoundException;
import com.ulsub.order.mapper.OrderMapper;
import com.ulsub.order.repository.PurchaseOrderRepository;
import com.ulsub.order.utils.Prototype;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderMapper orderMapper;

    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @InjectMocks
    private OrderService orderService;

    private final Prototype prototype = new Prototype();

    private PurchaseOrder createMockPurchaseOrderEntity(Long id) {
        PurchaseOrder po = mock(PurchaseOrder.class);
        lenient().when(po.getPurchaseOrderId()).thenReturn(id);
        return po;
    }

    @Test
    void addOrder_shouldReturnPurchaseOrderIdDto_whenOrderSaved() {
        PurchaseOrderDto dto = prototype.createPurchaseOrderDto();
        PurchaseOrder entity = createMockPurchaseOrderEntity(42L);
        when(orderMapper.mapOrderDtoToEntity(dto)).thenReturn(entity);
        when(purchaseOrderRepository.save(entity)).thenReturn(entity);

        PurchaseOrderIdDto idDto = orderService.addOrder(dto);

        assertThat(idDto).isNotNull();
        assertThat(idDto.purchaseOrderId()).isEqualTo(42L);
        then(orderMapper).should().mapOrderDtoToEntity(dto);
        then(purchaseOrderRepository).should().save(entity);
    }

    @Test
    void findAllOrders_shouldReturnListOfPurchaseOrderDtos() {
        PurchaseOrder entity1 = createMockPurchaseOrderEntity(1L);
        PurchaseOrder entity2 = createMockPurchaseOrderEntity(2L);
        when(purchaseOrderRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(orderMapper.mapOrderEntityToDto(entity1)).thenReturn(mock(PurchaseOrderDto.class));
        when(orderMapper.mapOrderEntityToDto(entity2)).thenReturn(mock(PurchaseOrderDto.class));

        List<PurchaseOrderDto> result = orderService.findAllOrders();

        assertThat(result).hasSize(2);
        then(purchaseOrderRepository).should().findAll();
        then(orderMapper).should().mapOrderEntityToDto(entity1);
        then(orderMapper).should().mapOrderEntityToDto(entity2);
    }

    @Test
    void deleteOrderById_shouldDeleteOrder_whenOrderExists() {
        PurchaseOrder entity = createMockPurchaseOrderEntity(5L);
        when(purchaseOrderRepository.findById(5L)).thenReturn(Optional.of(entity));

        orderService.deleteOrderById(5L);

        then(purchaseOrderRepository).should().findById(5L);
        then(purchaseOrderRepository).should().delete(entity);
    }

    @Test
    void deleteOrderById_shouldThrowEntityNotFoundException_whenOrderDoesNotExist() {
        when(purchaseOrderRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.deleteOrderById(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Purchase order with id 999 not found");

        then(purchaseOrderRepository).should().findById(999L);
        then(purchaseOrderRepository).should(never()).delete(any());
    }
}
