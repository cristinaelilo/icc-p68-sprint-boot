package ec.edu.ups.icc.fundamentos01.users.services; // Define la ruta/paquete donde reside esta interfaz.
import ec.edu.ups.icc.fundamentos01.users.dto.*;   // Importa los objetos de transferencia de datos necesarios.
import java.util.List;                            // Importa la clase para manejar listas de objetos.

public interface UserService {                    // Declara la interfaz que define las operaciones de negocio.

    List<UserResponseDto> findAll();               // Método para obtener una lista de todos los usuarios.
    UserResponseDto findOne(Long id);             // Método para buscar un usuario específico por su ID.
    UserResponseDto create(CreateUserDto dto);    // Método para guardar un usuario nuevo.
    UserResponseDto update(Long id, UpdateUserDto dto); // Método para modificar todos los datos de un usuario.
    UserResponseDto partialUpdate(Long id, PartialUpdateUserDto dto); // Método para actualizar solo algunos campos.
    void delete(Long id);                         // Método para eliminar un usuario por su ID.
}                                                 // Cierre de la definición de la interfaz.
