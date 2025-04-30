package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.exceptions.UserDuplicateException;
import br.com.dubacchiega.library.users.entities.DTO.UserRequestDTO;
import br.com.dubacchiega.library.users.entities.Mappers.UserRequestMapper;
import br.com.dubacchiega.library.users.entities.DTO.UserResponseDTO;
import br.com.dubacchiega.library.users.entities.Mappers.UserResponseMapper;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.entities.enums.UserRole;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UsersRepository usersRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO userRegistration(UserRequestDTO userRequestDTO){
        usersRepository.findByEmail(userRequestDTO.email()).ifPresent(
                usersEntity -> {
                    throw new UserDuplicateException("User already registered");
                }
        );
        UsersEntity newUser = userRequestMapper.toEntity(userRequestDTO);
        newUser.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        if (newUser.getRole() == null) {newUser.setRole(UserRole.USER);}
        usersRepository.save(newUser);
        return userResponseMapper.toDTO(newUser);
    }
}
