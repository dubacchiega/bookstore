package br.com.dubacchiega.library.users.entities.Mappers;

import br.com.dubacchiega.library.users.entities.DTO.UserResponseDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserRequestMapper INSTANCE = Mappers.getMapper(UserRequestMapper.class);

    UsersEntity toEntity(UserResponseDTO userResponseDTO);
    UserResponseDTO toDTO(UsersEntity usersEntity);
}
