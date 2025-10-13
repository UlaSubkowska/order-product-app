package com.ulsub.product;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product", schema = "quarkus")
public class ProductEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "quantity_total", nullable = false)
    private BigDecimal quantityTotal;

    @Column(name = "quantity_reserved", nullable = false)
    private BigDecimal quantityReserved;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private ZonedDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on", insertable = false)
    private ZonedDateTime updatedOn;
}
