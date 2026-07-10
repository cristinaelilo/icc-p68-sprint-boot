package ec.edu.ups.icc.fundamentos01.categories.dtos;

// Clase que representa la información que se enviará como respuesta al cliente
// (Response = Respuesta)
public class CategoryResponseDto {

    // Almacena el identificador único de la categoría
    private Long id;

    // Almacena el nombre de la categoría
    private String name;

    // Almacena la descripción de la categoría
    private String description;

    // Constructor vacío
    // Spring Boot y otras librerías lo utilizan para crear objetos automáticamente
    public CategoryResponseDto() {
    }

    // Constructor con parámetros
    // Permite crear un objeto enviando todos los datos de una sola vez
    public CategoryResponseDto(Long id,
                               String name,
                               String description) {

        // Asigna el valor recibido al atributo id
        this.id = id;

        // Asigna el nombre recibido al atributo name
        this.name = name;

        // Asigna la descripción recibida al atributo description
        this.description = description;
    }

    // Devuelve el valor del atributo id
    public Long getId() {
        return id;
    }

    // Modifica el valor del atributo id
    public void setId(Long id) {
        this.id = id;
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
