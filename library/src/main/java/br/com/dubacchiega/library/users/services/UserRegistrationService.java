package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.DTO.UserRequestDTO;
import br.com.dubacchiega.library.users.entities.Mappers.UserRequestMapper;
import br.com.dubacchiega.library.users.entities.DTO.UserResponseDTO;
import br.com.dubacchiega.library.users.entities.Mappers.UserResponseMapper;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserRequestMapper userRequestMapper;

    @Autowired
    UserResponseMapper userResponseMapper;

    public UserResponseDTO userRegistration(UserRequestDTO userRequestDTO){
        usersRepository.findByEmail(userRequestDTO.email()).ifPresent(
                usersEntity -> {
                    throw new UserException("Existing user!");
                }
        );
        UsersEntity newUser = userRequestMapper.toEntity(userRequestDTO);
        usersRepository.save(newUser);
        return userResponseMapper.toDTO(newUser);
    }
}
