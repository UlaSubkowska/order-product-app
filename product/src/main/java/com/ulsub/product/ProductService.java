package com.ulsub.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    Long addProduct(ProductDto productDto) {
        ProductEntity product = productMapper.toProductEntity(productDto);
        productRepository.persist(product);

        return productMapper.mapToProductDto(product).id();
    }

    List<ProductDto> findAllProducts() {
        List<ProductEntity> products = productRepository.findAll().stream().toList();
        return products.stream().map(productMapper::mapToProductDto).toList();
    }

    @Transactional
    void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
