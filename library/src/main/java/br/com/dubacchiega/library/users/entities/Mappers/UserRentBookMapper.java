package br.com.dubacchiega.library.users.entities.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRentBookMapper {

    UserRentBookMapper INSTANCE = Mappers.getMapper(UserRentBookMapper.class);

//    UsersEntity toEntity(UserRentBookDTO userRentBookDTO);
//    UserRentBookDTO toDTO(UsersEntity usersEntity);
}
