package com.ulsub.order;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ulsub.order.dto.PurchaseOrderDto;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> addOrder(@RequestBody @Valid PurchaseOrderDto purchaseOrderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("purchaseOrderId", orderService.addOrder(purchaseOrderDto)));
    }

    @GetMapping
    List<PurchaseOrderDto> findAllOrders() {
        return orderService.findAllOrders();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Long>> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
