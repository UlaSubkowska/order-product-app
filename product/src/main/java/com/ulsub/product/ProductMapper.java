package com.ulsub.product;

import com.ulsub.product.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductEntity toProductEntity(ProductDto productDto);

    ProductDto mapToProductDto(ProductEntity productEntity);
}
