package com.ulsub.order.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderIdDto;
import com.ulsub.order.exception.EntityNotFoundException;
import com.ulsub.order.utils.Prototype;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class OrderServiceIT {

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.4")
            .withDatabaseName("test_sales_order")
            .withUsername("test_user")
            .withPassword("test_pass")
            .withInitScript("init-db.sql");

    @Autowired
    private OrderService orderService;

    private PurchaseOrderDto sampleOrder;

    private final Prototype prototype = new Prototype();

    @BeforeEach
    void setup() {
        sampleOrder = prototype.createPurchaseOrderDto();
    }

    @Test
    @Transactional
    void testAddFindDeleteOrder() {
        // Add order
        PurchaseOrderIdDto orderIdDto = orderService.addOrder(sampleOrder);
        Long id = orderIdDto.purchaseOrderId();
        assertThat(orderIdDto).isNotNull();

        // Find all orders; check it contains the one you added
        List<PurchaseOrderDto> allOrders = orderService.findAllOrders();
        assertEquals(allOrders.getFirst().purchaseOrderId(), id);

        // Delete the order
        orderService.deleteOrderById(id);

        // Verify deletion
        assertThrows(EntityNotFoundException.class, () -> orderService.deleteOrderById(id));
    }
}
