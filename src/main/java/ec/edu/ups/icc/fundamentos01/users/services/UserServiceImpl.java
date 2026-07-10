package ec.edu.ups.icc.fundamentos01.users.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.core.exceptions.domain.ConflictException;
import ec.edu.ups.icc.fundamentos01.core.exceptions.domain.NotFoundException;
import ec.edu.ups.icc.fundamentos01.users.dto.CreateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dto.PartialUpdateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dto.UpdateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dto.UserResponseDto;
import ec.edu.ups.icc.fundamentos01.users.entity.UserEntity;
import ec.edu.ups.icc.fundamentos01.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return repo.findAll()
                .stream()
                .filter(user -> !user.isDeleted())
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponseDto findOne(Long id) {

        UserEntity entity = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return toResponse(entity);
    }

    @Override
    public UserResponseDto create(CreateUserDto dto) {

        if (repo.findByEmailIgnoreCaseAndDeletedFalse(dto.getEmail()).isPresent()) {
            throw new ConflictException("Email already registered");
        }

        UserEntity entity = new UserEntity();

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        // Simulación de hash
        entity.setPasswordHash("HASH_" + dto.getPassword());

        return toResponse(repo.save(entity));
    }

    @Override
    public UserResponseDto update(Long id, UpdateUserDto dto) {

        UserEntity entity = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        repo.findByEmailIgnoreCaseAndDeletedFalse(dto.getEmail())
                .ifPresent(user -> {

                    if (!user.getId().equals(id)) {
                        throw new ConflictException("Email already registered");
                    }

                });

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        return toResponse(repo.save(entity));
    }

    @Override
    public UserResponseDto partialUpdate(Long id, PartialUpdateUserDto dto) {

        UserEntity entity = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getEmail() != null) {

            repo.findByEmailIgnoreCaseAndDeletedFalse(dto.getEmail())
                    .ifPresent(user -> {

                        if (!user.getId().equals(id)) {
                            throw new ConflictException("Email already registered");
                        }

                    });

            entity.setEmail(dto.getEmail());
        }

        return toResponse(repo.save(entity));
    }

    @Override
    public void delete(Long id) {

        UserEntity entity = repo.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        entity.setDeleted(true);

        repo.save(entity);
    }

    private UserResponseDto toResponse(UserEntity entity) {

        UserResponseDto dto = new UserResponseDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());

        return dto;
    }

}
