package com.ulsub.order;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ulsub.order.dto.PurchaseOrderDto;
import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<Long> addOrder(@RequestBody @Valid PurchaseOrderDto purchaseOrderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(purchaseOrderDto));
    }

    @GetMapping
    List<PurchaseOrderDto> findAllOrders() {
        return orderService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.deleteById(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(id);
        }
    }
}
