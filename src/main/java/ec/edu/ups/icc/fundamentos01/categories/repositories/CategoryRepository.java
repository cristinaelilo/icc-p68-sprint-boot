package ec.edu.ups.icc.fundamentos01.categories.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.ups.icc.fundamentos01.categories.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    /**
     * Busca una categoría activa por nombre.
     */
    Optional<CategoryEntity> findByNameIgnoreCaseAndDeletedFalse(String name);

    /**
     * Verifica si existe una categoría activa con ese nombre.
     */
    boolean existsByNameIgnoreCaseAndDeletedFalse(String name);

    /**
     * Verifica si existe una categoría activa por id.
     */
    boolean existsByIdAndDeletedFalse(Long id);

    /**
     * Lista únicamente categorías no eliminadas.
     */
    List<CategoryEntity> findByDeletedFalse();

}
