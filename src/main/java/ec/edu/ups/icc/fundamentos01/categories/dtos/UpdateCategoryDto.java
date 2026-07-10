package ec.edu.ups.icc.fundamentos01.categories.dtos;

// Importa la validación que verifica que un campo no esté vacío
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO utilizado para recibir los datos cuando se actualiza una categoría
public class UpdateCategoryDto {

    // Verifica que el nombre no sea nulo, vacío o solo espacios
    @NotBlank(message = "El nombre es obligatorio")

    // Verifica que el nombre tenga entre 3 y 120 caracteres
    @Size(min = 3, max = 120,
            message = "El nombre debe tener entre 3 y 120 caracteres")

    // Almacena el nombre de la categoría
    private String name;

    // Verifica que la descripción no tenga más de 500 caracteres
    @Size(max = 500,
            message = "La descripción no debe superar los 500 caracteres")

    // Almacena la descripción de la categoría
    private String description;

    // Constructor vacío
    // Spring Boot lo utiliza para crear automáticamente el objeto
    public UpdateCategoryDto() {
    }

    // Constructor con parámetros
    // Permite crear un objeto con nombre y descripción
    public UpdateCategoryDto(String name, String description) {

        // Asigna el nombre recibido al atributo
        this.name = name;

        // Asigna la descripción recibida al atributo
        this.description = description;
    }

    // Devuelve el nombre de la categoría
    public String getName() {
        return name;
    }

    // Modifica el nombre de la categoría
    public void setName(String name) {
        this.name = name;
    }

    // Devuelve la descripción de la categoría
    public String getDescription() {
        return description;
    }

    // Modifica la descripción de la categoría
    public void setDescription(String description) {
        this.description = description;
    }

}
