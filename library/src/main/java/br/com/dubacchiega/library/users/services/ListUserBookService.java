package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;

import br.com.dubacchiega.library.books.entities.DTO.BookRentedByUserDTO;
import br.com.dubacchiega.library.books.entities.Mappers.BookRentedByUserMapper;

import br.com.dubacchiega.library.config.JWTUserData;
import br.com.dubacchiega.library.exceptions.UserDuplicateException;
import br.com.dubacchiega.library.exceptions.UserNotFoundException;
import br.com.dubacchiega.library.users.entities.DTO.UserRentBookResponseDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListUserBookService {

    private final UsersRepository usersRepository;
    private final BookRentedByUserMapper bookRentedByUserMapper;

    public UserRentBookResponseDTO listUserBook(){

        JWTUserData userData = AuthService.getUser();

        UsersEntity user = usersRepository.findById(userData.id()).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        List<BooksEntity> booksEntities = user.getBooks();
        List<BookRentedByUserDTO> bookRentedByUserDTO = booksEntities.stream()
                .map(bookRentedByUserMapper::toDTO)
                .collect(Collectors.toList());

        return new UserRentBookResponseDTO(user.getName(), user.getUsername(), user.getEmail(), bookRentedByUserDTO);
    }
}
