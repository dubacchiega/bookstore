package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.UserRequestDTO;
import br.com.dubacchiega.library.users.entities.UserResponseDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    UsersRepository usersRepository;

    public UserResponseDTO userRegistration(UserRequestDTO user){
        usersRepository.findByEmail(user.email()).ifPresent(
                usersEntity -> {
                    throw new UserException("Existing user!");
                }
        );
        UsersEntity newUser = usersRepository.save(new UsersEntity(user));
        return UserResponseDTO.builder()
                .name(newUser.getName())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .createdAt(newUser.getCreatedAt())
                .build();
    }
}
