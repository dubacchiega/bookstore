package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTOsMappers.RentBookDTO;
import br.com.dubacchiega.library.books.entities.DTOsMappers.RentBookResponseDTO;
import br.com.dubacchiega.library.books.repositories.BooksRepository;
import br.com.dubacchiega.library.exceptions.BookException;
import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.DTOsMappers.UserRentBookDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RentBookService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BooksRepository booksRepository;

    public UserRentBookDTO rentBook(UUID userId, RentBookDTO bookDTO){


        UsersEntity user = usersRepository.findById(userId).orElseThrow(
                () -> {throw new UserException("User not found");});

        BooksEntity book = booksRepository.findByTitle(bookDTO.title()).orElseThrow(
                () -> {throw new BookException("Book not found");});

        if (book.getAvailable() && book.getStock() > 0){
            user.setBooks(book);

            book.setStock(book.getStock() - 1);

            booksRepository.save(book);
            usersRepository.save(user);

            RentBookResponseDTO booksResponseDTO = new RentBookResponseDTO(book.getTitle(), book.getAuthor(), book.getStock());

            UserRentBookDTO userRentBookDTO = new UserRentBookDTO(user.getName(), user.getUsername(), user.getEmail(), booksResponseDTO);
            return userRentBookDTO;
        }else {
            throw new BookException("Book unavailable");
        }

    }
}
