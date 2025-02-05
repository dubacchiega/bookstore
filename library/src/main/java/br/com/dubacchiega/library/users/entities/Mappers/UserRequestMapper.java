package br.com.dubacchiega.library.users.entities.Mappers;

import br.com.dubacchiega.library.users.entities.DTO.UserRequestDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    UserRequestMapper INSTANCE = Mappers.getMapper(UserRequestMapper.class);

    UsersEntity toEntity(UserRequestDTO userRequestDTO);
    UserRequestDTO toDTO(UsersEntity usersEntity);
}
