package ec.edu.ups.icc.fundamentos01.users.mappers;

import ec.edu.ups.icc.fundamentos01.users.dto.*;
import ec.edu.ups.icc.fundamentos01.users.entity.UserEntity;
import ec.edu.ups.icc.fundamentos01.users.models.UserModel;

public class UserMapper {

    // DTO → MODEL
    public static UserModel toModelFromDTO(CreateUserDto dto) {

        UserModel model = new UserModel();
        model.setName(dto.getName());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        model.setPasswordHash("HASH_" + dto.getPassword());

        return model;
    }

    // ENTITY → MODEL
    public static UserModel toModelFromEntity(UserEntity entity) {

        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setEmail(entity.getEmail());
        model.setPasswordHash(entity.getPasswordHash());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setDeleted(entity.isDeleted());

        return model;
    }

    // MODEL → ENTITY
    public static UserEntity toEntityFromModel(UserModel model) {

        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setEmail(model.getEmail());
        entity.setPasswordHash(model.getPasswordHash());

        return entity;
    }

    // MODEL → RESPONSE DTO
    public static UserResponseDto toResponse(UserModel model) {

        UserResponseDto dto = new UserResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());

        return dto;
    }
}