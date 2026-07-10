package ec.edu.ups.icc.fundamentos01.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.ups.icc.fundamentos01.users.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /*
     * Buscar por email (sin importar mayúsculas/minúsculas)
     */
    Optional<UserEntity> findByEmailIgnoreCase(String email);

    /*
     * Buscar por email únicamente si no está eliminado
     */
    Optional<UserEntity> findByEmailIgnoreCaseAndDeletedFalse(String email);

    /*
     * Buscar usuario activo por ID
     */
    Optional<UserEntity> findByIdAndDeletedFalse(Long id);

    /*
     * Verificar existencia de usuario activo
     */
    boolean existsByIdAndDeletedFalse(Long id);

    Optional<UserEntity> findByEmailAndDeletedFalse(String email);

    boolean existsByEmail(String email);

}

