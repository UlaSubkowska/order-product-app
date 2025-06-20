package com.ulsub.order.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "sales_order", name = "purchase_order_item")
public class PurchaseOrderItem {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseOrderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    private PurchaseOrder purchaseOrder;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "price_item", nullable = false)
    private BigDecimal priceItem;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}
