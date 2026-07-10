package ec.edu.ups.icc.fundamentos01.categories.services;

import java.util.List;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoryResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoryDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.UpdateCategoryDto;

public interface CategoryService {

    /**
     * Obtiene todas las categorías activas.
     */
    List<CategoryResponseDto> findAll();

    /**
     * Obtiene una categoría por su ID.
     */
    CategoryResponseDto findOne(Long id);

    /**
     * Crea una nueva categoría.
     */
    CategoryResponseDto create(CreateCategoryDto dto);

    /**
     * Actualiza completamente una categoría.
     */
    CategoryResponseDto update(Long id, UpdateCategoryDto dto);

    /**
     * Elimina lógicamente una categoría.
     */
    void delete(Long id);

}