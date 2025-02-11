package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.BookRentedByUserDTO;
import br.com.dubacchiega.library.books.entities.DTO.RentBookDTO;
import br.com.dubacchiega.library.books.entities.DTO.RentBookResponseDTO;
import br.com.dubacchiega.library.books.entities.Mappers.BookRentedByUserMapper;
import br.com.dubacchiega.library.books.repositories.BooksRepository;
import br.com.dubacchiega.library.exceptions.BookException;
import br.com.dubacchiega.library.exceptions.UserBookException;
import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.DTO.UserRentBookDTO;
import br.com.dubacchiega.library.users.entities.DTO.UserRentBookResponseDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentBookService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    BookRentedByUserMapper bookRentedByUserMapper;

    public UserRentBookDTO rentBook(UUID userId, RentBookDTO bookDTO){


        UsersEntity user = usersRepository.findById(userId).orElseThrow(
                () -> {throw new UserException("User not found");});

        BooksEntity book = booksRepository.findByTitle(bookDTO.title()).orElseThrow(
                () -> {throw new BookException("Book not found");});

        if (book.getAvailable() && book.getStock() > 0){
            user.addBooks(book);

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

    public UserRentBookResponseDTO returnBook(UUID userId, RentBookDTO title){
        UsersEntity user = usersRepository.findById(userId).orElseThrow(
                () -> {throw new UserException("User not found");});

        BooksEntity book = booksRepository.findByTitle(title.title()).orElseThrow(
                () -> {throw new BookException("Book not found");});

        List<BooksEntity> userBooks = new ArrayList<>(user.getBooks());
        if (userBooks.isEmpty()){
            throw new UserBookException("No books rented");
        }

        boolean removed = userBooks.removeIf(b -> b.getTitle().equals(title.title()));

        if (!removed) {
            throw new UserBookException("Book not found in user's rented list");
        }

        user.setBooks(new ArrayList<>(userBooks));
        usersRepository.save(user);

        book.setStock(book.getStock() + 1);
        booksRepository.save(book);

        List<BookRentedByUserDTO> bookRentedByUserDTO = userBooks.stream()
                .map(bookRentedByUserMapper::toDTO)
                .collect(Collectors.toList());

        return new UserRentBookResponseDTO(user.getName(), user.getUsername(), user.getEmail(), bookRentedByUserDTO);
    }
}
