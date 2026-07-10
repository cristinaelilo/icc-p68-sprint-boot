package ec.edu.ups.icc.fundamentos01.categories.dtos;

// Importa la anotación que valida que un campo no esté vacío
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO utilizado para recibir los datos necesarios para crear una categoría
public class CreateCategoryDto {

    // Valida que el nombre no sea nulo, vacío o solo espacios
    @NotBlank(message = "El nombre es obligatorio")

    // Valida que el nombre tenga entre 3 y 120 caracteres
    @Size(min = 3, max = 120,
            message = "El nombre debe tener entre 3 y 120 caracteres")

    // Guarda el nombre de la categoría
    private String name;

    // Valida que la descripción tenga como máximo 500 caracteres
    @Size(max = 500,
            message = "La descripción no debe superar los 500 caracteres")

    // Guarda la descripción de la categoría
    private String description;

    // Constructor vacío
    // Spring Boot lo utiliza para crear el objeto automáticamente
    public CreateCategoryDto() {
    }

    // Constructor con parámetros
    // Permite crear un objeto enviando el nombre y la descripción
    public CreateCategoryDto(String name, String description) {

        // Asigna el nombre recibido al atributo name
        this.name = name;

        // Asigna la descripción recibida al atributo description
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
