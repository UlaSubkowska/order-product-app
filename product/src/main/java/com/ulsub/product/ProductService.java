package com.ulsub.product;

import com.ulsub.product.dto.ProductDto;
import com.ulsub.product.exception.EntityNotFoundException;
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
    public Long addProduct(ProductDto productDto) {
        ProductEntity product = productMapper.toProductEntity(productDto);
        productRepository.persist(product);

        return productMapper.mapToProductDto(product).id();
    }

    public List<ProductDto> findAllProducts() {
        List<ProductEntity> products = productRepository.findAll().stream().toList();
        return products.stream().map(productMapper::mapToProductDto).toList();
    }

    public ProductDto findProduct(Long id) {
        return productRepository
                .findByIdOptional(id)
                .map(productMapper::mapToProductDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with id %s not found.", id)));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
