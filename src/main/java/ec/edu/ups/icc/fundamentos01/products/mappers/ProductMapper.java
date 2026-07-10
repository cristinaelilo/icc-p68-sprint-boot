package ec.edu.ups.icc.fundamentos01.products.mappers;

// Importa el DTO utilizado para crear un producto
import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.products.models.ProductModel;

// Clase encargada de convertir objetos entre DTO, Entity y Model
public class ProductMapper {

    // ===============================
    // DTO -> ENTITY
    // ===============================
    // Convierte un CreateProductDto en un ProductEntity
    public static ProductEntity toEntity(CreateProductDto dto) {

        // Crea una nueva entidad
        ProductEntity entity = new ProductEntity();

        // Copia el nombre
        entity.setName(dto.getName());

        // Copia el precio
        entity.setPrice(dto.getPrice());

        // Copia el stock
        entity.setStock(dto.getStock());

        // Devuelve la entidad
        return entity;
    }

    // ===============================
    // ENTITY -> MODEL
    // ===============================
    // Convierte una entidad en un modelo
    public static ProductModel toModelFromEntity(ProductEntity entity) {

        // Crea un nuevo modelo
        ProductModel model = new ProductModel();

        // Copia el ID
        model.setId(entity.getId());

        // Copia el nombre
        model.setName(entity.getName());

        // Copia el precio
        model.setPrice(entity.getPrice());

        // Copia el stock
        model.setStock(entity.getStock());

        // Copia el estado de borrado lógico
        model.setDeleted(entity.isDeleted());

        // Devuelve el modelo
        return model;
    }

    // ===============================
    // MODEL -> RESPONSE DTO
    // ===============================
    // Convierte un modelo en un DTO de respuesta
    public static ProductResponseDto toResponse(ProductModel model) {

        // Crea un nuevo DTO
        ProductResponseDto dto = new ProductResponseDto();

        // Copia el ID
        dto.setId(model.getId());

        // Copia el nombre
        dto.setName(model.getName());

        // Copia el precio
        dto.setPrice(model.getPrice());

        // Copia el stock
        dto.setStock(model.getStock());

        // Devuelve el DTO
        return dto;
    }
}
