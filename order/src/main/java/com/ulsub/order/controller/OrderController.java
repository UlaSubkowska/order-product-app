package com.ulsub.order.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ulsub.order.dto.PurchaseOrderDto;
import com.ulsub.order.dto.PurchaseOrderIdDto;
import com.ulsub.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Orders")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaseOrderIdDto> addOrder(@RequestBody @Valid PurchaseOrderDto purchaseOrderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(purchaseOrderDto));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderDto>> findAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
