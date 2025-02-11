package br.com.dubacchiega.library.users.entities.Mappers;

import br.com.dubacchiega.library.users.entities.DTO.UserRentedMoreResponseDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRentedMoreResponseMapper {

    UserRentedMoreResponseMapper INSTANCE = Mappers.getMapper(UserRentedMoreResponseMapper.class);

    UsersEntity toEntity(UserRentedMoreResponseDTO userRentedMoreResponseDTO);
    UserRentedMoreResponseDTO toDTO(UsersEntity usersEntity);
}
