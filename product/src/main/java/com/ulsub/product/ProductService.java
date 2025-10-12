package com.ulsub.product;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

    Long addProduct(ProductDto productDto) {
        //TODO implement
        return 1L;
    }

    List<ProductDto> findAllProducts() {
        //TODO implement
        return new ArrayList<>();
    }

    void deleteProduct(Long id) {
        //TODO implement
    }
}
