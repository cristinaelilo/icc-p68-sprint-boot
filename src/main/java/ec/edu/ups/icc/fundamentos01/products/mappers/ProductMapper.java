package ec.edu.ups.icc.fundamentos01.products.mappers;

import ec.edu.ups.icc.fundamentos01.products.dtos.*;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;

public class ProductMapper {

    // DTO -> ENTITY
    public static ProductEntity toEntity(CreateProductDto dto) {
        ProductEntity entity = new ProductEntity();

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());

        return entity;
    }

    // ENTITY -> DTO RESPONSE
    public static ProductResponseDto toResponse(ProductEntity entity) {

        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());

        return dto;
    }
}