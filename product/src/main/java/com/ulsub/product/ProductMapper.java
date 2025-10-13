package com.ulsub.product;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    ProductEntity toProduct(ProductDto productDto);

    @InheritInverseConfiguration(name = "toProduct")
    ProductDto toProductDto(ProductEntity productEntity);
}
