package com.ulsub.order.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderIdDto;
import com.ulsub.order.service.OrderService;
import com.ulsub.order.utils.Prototype;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
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

    private final Prototype prototype = new Prototype();
    private static final Long PURCHASE_ORDER_ID = 1L;

    @BeforeEach
    public void setupTest() {
        given(orderService.addOrder(any(PurchaseOrderDto.class))).willReturn(createPurchaseOrderId());
        given(orderService.findAllOrders())
                .willReturn(List.of(
                        prototype.createPurchaseOrderDto_withId(), prototype.createPurchaseOrderDto_withId_second()));
        doNothing().when(orderService).deleteOrderById(any(Long.class));

        RestAssuredMockMvc.standaloneSetup(orderController, new GlobalExceptionHandler());
    }

    private PurchaseOrderIdDto createPurchaseOrderId() {
        return new PurchaseOrderIdDto(PURCHASE_ORDER_ID);
    }
}
