package ec.edu.ups.icc.fundamentos01.products.dtos;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;

public class UpdateProductDto {

    private String name;
    private Double price;
    private Integer stock;

    @NotEmpty(message = "Debe seleccionar al menos una categoría")
    private Set<Long> categoryIds;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Set<Long> getCategoryIds() { return categoryIds; }
    public void setCategoryIds(Set<Long> categoryIds) { this.categoryIds = categoryIds; }
}
