package com.ulsub.order.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderIdDto;
import com.ulsub.order.dto.PurchaseOrderItemDto;
import com.ulsub.order.entity.PurchaseOrder;
import com.ulsub.order.exception.EntityNotFoundException;
import com.ulsub.order.mapper.OrderMapper;
import com.ulsub.order.repository.PurchaseOrderRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private PurchaseOrder createMockPurchaseOrderEntity(Long id) {
        PurchaseOrder po = mock(PurchaseOrder.class);
        lenient().when(po.getPurchaseOrderId()).thenReturn(id);
        return po;
    }

    @Test
    void addOrder_shouldReturnPurchaseOrderIdDto_whenOrderSaved() {
        PurchaseOrderDto dto = createPurchaseOrderDto();
        PurchaseOrder entity = createMockPurchaseOrderEntity(42L);

        when(orderMapper.mapOrderDtoToEntity(dto)).thenReturn(entity);
        when(purchaseOrderRepository.save(entity)).thenReturn(entity);

        PurchaseOrderIdDto idDto = orderService.addOrder(dto);

        assertThat(idDto).isNotNull();
        assertThat(idDto.purchaseOrderId()).isEqualTo(42L);

        verify(orderMapper).mapOrderDtoToEntity(dto);
        verify(purchaseOrderRepository).save(entity);
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
        verify(purchaseOrderRepository).findAll();
        verify(orderMapper).mapOrderEntityToDto(entity1);
        verify(orderMapper).mapOrderEntityToDto(entity2);
    }

    @Test
    void deleteOrderById_shouldDeleteOrder_whenOrderExists() {
        PurchaseOrder entity = createMockPurchaseOrderEntity(5L);

        when(purchaseOrderRepository.findById(5L)).thenReturn(Optional.of(entity));

        orderService.deleteOrderById(5L);

        verify(purchaseOrderRepository).findById(5L);
        verify(purchaseOrderRepository).delete(entity);
    }

    @Test
    void deleteOrderById_shouldThrowEntityNotFoundException_whenOrderDoesNotExist() {
        when(purchaseOrderRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.deleteOrderById(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Purchase order with id 999 not found");

        verify(purchaseOrderRepository).findById(999L);
        verify(purchaseOrderRepository, never()).delete(any());
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
