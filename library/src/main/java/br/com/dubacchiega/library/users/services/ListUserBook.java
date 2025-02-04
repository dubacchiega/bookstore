package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListUserBook {

    @Autowired
    UsersRepository usersRepository;

    public UsersEntity listUserBook(UUID id){
        return usersRepository.findById(id).orElseThrow(
                () -> new UserException("User not found")
        );
    }
}
