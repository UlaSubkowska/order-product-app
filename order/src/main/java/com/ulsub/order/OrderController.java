package com.ulsub.order;

import com.ulsub.order.dto.PurchaseOrderDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders")
    public Long addOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return orderService.addOrder(purchaseOrderDto);
    }

    @GetMapping("/orders")
    List<PurchaseOrderDto> findAllOrders() {
        return orderService.findAll();
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteById(id);
            return ResponseEntity.ok("Order with id: " + id + " deleted");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The order with id: " + id + " doesn't exist");
        }
    }
}
