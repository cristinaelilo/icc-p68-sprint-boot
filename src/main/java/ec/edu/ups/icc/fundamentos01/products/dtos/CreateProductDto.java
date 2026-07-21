package ec.edu.ups.icc.fundamentos01.products.dtos;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

/*
 * DTO utilizado para recibir los datos necesarios para crear un producto.
 *
 * PRÁCTICA 13: Se eliminó el campo userId.
 *
 * El owner del producto ya NO se recibe desde el cliente.
 * Se obtiene del usuario autenticado (token JWT) en el servicio,
 * para evitar que un usuario cree productos a nombre de otro.
 */
@Schema(description = "Datos necesarios para crear un nuevo producto")
public class CreateProductDto {

    @Schema(
            description = "Nombre del producto",
            example = "Laptop ASUS Vivobook 16"
    )
    private String name;

    @Schema(
            description = "Precio del producto",
            example = "899.99"
    )
    private Double price;

    @Schema(
            description = "Cantidad disponible en inventario",
            example = "15"
    )
    private Integer stock;

    @Schema(
            description = "Identificadores de las categorías asociadas al producto",
            example = "[1, 2]"
    )
    @NotEmpty(message = "Debe seleccionar al menos una categoría")
    private Set<Long> categoryIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
