package com.ulsub.product;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    Long addProduct(ProductDto productDto) {
        ProductEntity product = productMapper.toProduct(productDto);
        productRepository.persist(product);

        return productMapper.toProductDto(product).id();
    }

    List<ProductDto> findAllProducts() {
        List<ProductEntity> products = productRepository.findAll().stream().toList();
        return products.stream().map(productMapper::toProductDto).toList();
    }

    void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
