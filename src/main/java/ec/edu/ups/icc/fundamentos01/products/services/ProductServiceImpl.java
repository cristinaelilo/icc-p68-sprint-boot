package ec.edu.ups.icc.fundamentos01.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.products.dtos.*;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.products.mappers.ProductMapper;
import ec.edu.ups.icc.fundamentos01.products.respositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .filter(p -> !p.isDeleted())
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponseDto findOne(Long id) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found"));

        return ProductMapper.toResponse(entity);
    }

    @Override
    public ProductResponseDto create(CreateProductDto dto) {
        ProductEntity entity = ProductMapper.toEntity(dto);
        ProductEntity saved = repository.save(entity);

        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponseDto update(Long id, UpdateProductDto dto) {

        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found"));

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());

        return ProductMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProductResponseDto partialUpdate(Long id, PartialUpdateProductDto dto) {

        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found"));

        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getStock() != null) entity.setStock(dto.getStock());

        return ProductMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long id) {

        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found"));

        entity.setDeleted(true);
        repository.save(entity);
    }
}