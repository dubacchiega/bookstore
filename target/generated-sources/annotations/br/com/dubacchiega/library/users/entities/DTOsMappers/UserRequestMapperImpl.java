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
public class UserRequestMapperImpl implements UserRequestMapper {

    @Override
    public UsersEntity toEntity(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        UsersEntity usersEntity = new UsersEntity();

        usersEntity.setName( userRequestDTO.name() );
        usersEntity.setUsername( userRequestDTO.username() );
        usersEntity.setEmail( userRequestDTO.email() );
        usersEntity.setPassword( userRequestDTO.password() );

        return usersEntity;
    }

    @Override
    public UserRequestDTO toDTO(UsersEntity usersEntity) {
        if ( usersEntity == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String username = null;
        String password = null;

        name = usersEntity.getName();
        email = usersEntity.getEmail();
        username = usersEntity.getUsername();
        password = usersEntity.getPassword();

        UserRequestDTO userRequestDTO = new UserRequestDTO( name, email, username, password );

        return userRequestDTO;
    }
}
