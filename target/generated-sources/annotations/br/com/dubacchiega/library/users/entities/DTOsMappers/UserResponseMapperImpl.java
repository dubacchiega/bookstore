package br.com.dubacchiega.library.users.entities.DTOsMappers;

import br.com.dubacchiega.library.users.entities.UsersEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-04T19:45:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserResponseMapperImpl implements UserResponseMapper {

    @Override
    public UsersEntity toEntity(UserResponseDTO userResponseDTO) {
        if ( userResponseDTO == null ) {
            return null;
        }

        UsersEntity usersEntity = new UsersEntity();

        usersEntity.setName( userResponseDTO.name() );
        usersEntity.setUsername( userResponseDTO.username() );
        usersEntity.setEmail( userResponseDTO.email() );
        usersEntity.setCreatedAt( userResponseDTO.createdAt() );

        return usersEntity;
    }

    @Override
    public UserResponseDTO toDTO(UsersEntity usersEntity) {
        if ( usersEntity == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.name( usersEntity.getName() );
        userResponseDTO.username( usersEntity.getUsername() );
        userResponseDTO.email( usersEntity.getEmail() );
        userResponseDTO.createdAt( usersEntity.getCreatedAt() );

        return userResponseDTO.build();
    }
}
