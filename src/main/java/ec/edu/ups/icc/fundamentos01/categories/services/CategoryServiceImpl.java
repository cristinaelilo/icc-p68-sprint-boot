package ec.edu.ups.icc.fundamentos01.categories.services;

// Importa la interfaz List para manejar listas
import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoryResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoryDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.UpdateCategoryDto;
import ec.edu.ups.icc.fundamentos01.categories.entities.CategoryEntity;
import ec.edu.ups.icc.fundamentos01.categories.repositories.CategoryRepository;
import ec.edu.ups.icc.fundamentos01.core.exceptions.domain.ConflictException;
import ec.edu.ups.icc.fundamentos01.core.exceptions.domain.NotFoundException;

// Marca esta clase como un Servicio
@Service
public class CategoryServiceImpl implements CategoryService {

    // Repositorio para acceder a la base de datos
    private final CategoryRepository categoryRepository;

    // Constructor
    // Spring inyecta automáticamente el repositorio
    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        // Guarda el repositorio en la variable
        this.categoryRepository = categoryRepository;
    }

    // Implementa el método definido en la interfaz
    @Override
    public List<CategoryResponseDto> findAll() {

        // Busca únicamente las categorías que NO están eliminadas
        return categoryRepository.findByDeletedFalse()

                // Convierte la lista en un Stream
                .stream()

                // Convierte cada CategoryEntity en CategoryResponseDto
                .map(this::toResponse)

                // Convierte nuevamente el Stream en una lista
                .toList();

    }

    @Override
    public CategoryResponseDto findOne(Long id) {

        // Busca la categoría por su ID
        CategoryEntity entity = categoryRepository.findById(id)

                // Si no existe lanza una excepción
                .orElseThrow(() ->
                        new NotFoundException("Category not found"));

        // Si la categoría fue eliminada lógicamente
        if (entity.isDeleted()) {

            // También devuelve Not Found
            throw new NotFoundException("Category not found");
        }

        // Convierte la entidad en DTO y la devuelve
        return toResponse(entity);

    }

    @Override
    public CategoryResponseDto create(CreateCategoryDto dto) {

        // Verifica si ya existe una categoría
        // con ese nombre y que no esté eliminada
        if (categoryRepository
                .existsByNameIgnoreCaseAndDeletedFalse(dto.getName())) {

            // Si existe genera un error 409 Conflict
            throw new ConflictException(
                    "Category name already registered");
        }

        // Crea una nueva entidad
        CategoryEntity entity = new CategoryEntity();

        // Copia el nombre recibido en el DTO
        entity.setName(dto.getName());

        // Copia la descripción
        entity.setDescription(dto.getDescription());

        // Guarda la entidad en la base de datos
        CategoryEntity saved = categoryRepository.save(entity);

        // Convierte la entidad guardada a DTO
        return toResponse(saved);

    }

    @Override
    public CategoryResponseDto update(Long id, UpdateCategoryDto dto) {

        // Busca la categoría
        CategoryEntity entity = categoryRepository.findById(id)

                // Si no existe lanza excepción
                .orElseThrow(() ->
                        new NotFoundException("Category not found"));

        // Si fue eliminada
        if (entity.isDeleted()) {

            throw new NotFoundException("Category not found");
        }

        // Actualiza el nombre
        entity.setName(dto.getName());

        // Actualiza la descripción
        entity.setDescription(dto.getDescription());

        // Guarda nuevamente la entidad
        CategoryEntity saved = categoryRepository.save(entity);

        // Devuelve la categoría actualizada
        return toResponse(saved);

    }

    @Override
    public void delete(Long id) {

        // Busca la categoría
        CategoryEntity entity = categoryRepository.findById(id)

                // Si no existe genera excepción
                .orElseThrow(() ->
                        new NotFoundException("Category not found"));

        // Si ya estaba eliminada
        if (entity.isDeleted()) {

            throw new NotFoundException("Category not found");
        }

        // Borrado lógico
        // Solo cambia deleted a true
        entity.setDeleted(true);

        // Guarda el cambio
        categoryRepository.save(entity);

    }

    /**
     * Convierte una entidad en un Response DTO.
     */
    private CategoryResponseDto toResponse(CategoryEntity entity) {

        // Crea un nuevo DTO
        CategoryResponseDto dto = new CategoryResponseDto();

        // Copia el ID
        dto.setId(entity.getId());

        // Copia el nombre
        dto.setName(entity.getName());

        // Copia la descripción
        dto.setDescription(entity.getDescription());

        // Devuelve el DTO
        return dto;

    }

}
