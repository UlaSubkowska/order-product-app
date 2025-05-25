package com.ulsub.order.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderIdDto;
import com.ulsub.order.dto.PurchaseOrderItemDto;
import com.ulsub.order.service.OrderService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = OrderController.class)
public class OrderControllerBase {

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    private static final Long PURCHASE_ORDER_ID = 1L;

    @BeforeEach
    public void setupTest() {
        // given
        given(orderService.addOrder(any(PurchaseOrderDto.class))).willReturn(createPurchaseOrderId());
        given(orderService.findAllOrders()).willReturn(List.of(createPurchaseOrder_one(), createPurchaseOrder_two()));
        doNothing().when(orderService).deleteOrderById(any(Long.class));

        // then
        RestAssuredMockMvc.standaloneSetup(orderController, new GlobalExceptionHandler());
    }

    private PurchaseOrderIdDto createPurchaseOrderId() {
        return new PurchaseOrderIdDto(PURCHASE_ORDER_ID);
    }

    private PurchaseOrderDto createPurchaseOrder_one() {
        return new PurchaseOrderDto(
                2L,
                3L,
                BigDecimal.valueOf(704.99).setScale(2, RoundingMode.HALF_UP),
                List.of(new PurchaseOrderItemDto(
                        3L,
                        BigDecimal.valueOf(1).setScale(1, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(704.99).setScale(2, RoundingMode.HALF_UP),
                        BigDecimal.valueOf(704.99).setScale(2, RoundingMode.HALF_UP))));
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

    private PurchaseOrderDto createPurchaseOrder_two() {
        return new PurchaseOrderDto(
                PURCHASE_ORDER_ID,
                1L,
                BigDecimal.valueOf(2369.97).setScale(2, RoundingMode.HALF_UP),
                createPurchaseOrderItems());
    }
}
