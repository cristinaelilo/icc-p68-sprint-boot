package ec.edu.ups.icc.fundamentos01.products.models;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;

public class ProductModel {

    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private boolean deleted;

    public static ProductModel fromEntity(ProductEntity e) {
        ProductModel m = new ProductModel();
        m.setId(e.getId());
        m.setName(e.getName());
        m.setPrice(e.getPrice());
        m.setStock(e.getStock());
        m.setDeleted(e.isDeleted());
        return m;
    }

    public static ProductModel fromCreateDto(CreateProductDto dto) {
        ProductModel m = new ProductModel();
        m.setName(dto.getName());
        m.setPrice(dto.getPrice());
        m.setStock(dto.getStock());
        return m;
    }

    public ProductEntity toEntity() {
        ProductEntity e = new ProductEntity();
        e.setId(this.id);
        e.setName(this.name);
        e.setPrice(this.price);
        e.setStock(this.stock);
        e.setDeleted(this.deleted);
        return e;
    }

    public ProductResponseDto toResponseDto() {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setPrice(this.price);
        dto.setStock(this.stock);
        return dto;
    }

    public void update(UpdateProductDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
    }

    public void partialUpdate(PartialUpdateProductDto dto) {
        if (dto.getName() != null) this.name = dto.getName();
        if (dto.getPrice() != null) this.price = dto.getPrice();
        if (dto.getStock() != null) this.stock = dto.getStock();
    }

    public void delete() {
        this.deleted = true;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
