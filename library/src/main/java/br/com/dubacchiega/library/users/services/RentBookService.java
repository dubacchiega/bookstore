package br.com.dubacchiega.library.users.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.BookRentedByUserDTO;
import br.com.dubacchiega.library.books.entities.DTO.RentBookDTO;
import br.com.dubacchiega.library.books.entities.DTO.RentBookResponseDTO;
import br.com.dubacchiega.library.books.entities.Mappers.BookRentedByUserMapper;
import br.com.dubacchiega.library.books.repositories.BooksRepository;
import br.com.dubacchiega.library.config.JWTUserData;
import br.com.dubacchiega.library.exceptions.*;
import br.com.dubacchiega.library.users.entities.DTO.UserRentBookDTO;
import br.com.dubacchiega.library.users.entities.DTO.UserRentBookResponseDTO;
import br.com.dubacchiega.library.users.entities.DTO.UserRentedMoreResponseDTO;
import br.com.dubacchiega.library.users.entities.Mappers.UserRentedMoreResponseMapper;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentBookService {

    private final UsersRepository usersRepository;
    private final BooksRepository booksRepository;
    private final BookRentedByUserMapper bookRentedByUserMapper;
    private final UserRentedMoreResponseMapper userRentedMoreResponseMapper;

    public UserRentBookDTO rentBook(RentBookDTO bookDTO){

        JWTUserData userData = AuthService.getUser();


        UsersEntity user = usersRepository.findById(userData.id()).orElseThrow(
                () -> {throw new UserNotFoundException("User not found");});

        BooksEntity book = booksRepository.findByTitle(bookDTO.title()).orElseThrow(
                () -> {throw new BookNotFoundException("Book not found");});

        if (usersRepository.existsByIdAndBooksId(user.getId(), book.getId())){
            throw new UserBookException("Book already rented by this user");
        }

        if (book.getAvailable() && book.getStock() > 0){
            user.addBooks(book);
            if (user.getRented_more() == null || book.getMostRentedRank() == null){
                user.setRented_more(0);
                book.setMostRentedRank(0);
            }
            user.setRented_more(user.getRented_more() + 1);
            book.setMostRentedRank(book.getMostRentedRank() + 1);

            book.setStock(book.getStock() - 1);

            booksRepository.save(book);
            usersRepository.save(user);

            RentBookResponseDTO booksResponseDTO = new RentBookResponseDTO(book.getTitle(), book.getAuthor(), book.getStock());

            UserRentBookDTO userRentBookDTO = new UserRentBookDTO(user.getName(), user.getUsername(), user.getEmail(), booksResponseDTO);
            return userRentBookDTO;
        }else {
            throw new BookUnavailableException("Book unavailable");
        }

    }

    public UserRentBookResponseDTO returnBook(RentBookDTO title){

        JWTUserData userData = AuthService.getUser();

        UsersEntity user = usersRepository.findById(userData.id()).orElseThrow(
                () -> {throw new UserNotFoundException("User not found");});

        BooksEntity book = booksRepository.findByTitle(title.title()).orElseThrow(
                () -> {throw new BookNotFoundException("Book not found");});

        List<BooksEntity> userBooks = new ArrayList<>(user.getBooks());
        if (userBooks.isEmpty()){
            throw new UserBookException("No books rented");
        }

        boolean removed = userBooks.removeIf(b -> b.getTitle().equals(title.title()));

        if (!removed) {
            throw new BookNotFoundException("Book not found in user's rented list");
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

    public List<UserRentedMoreResponseDTO> userRentedMostBook(){

        List<UsersEntity> users = usersRepository.findAll();
        int rentedMax = users.stream()
                .map(UsersEntity::getRented_more)
                .max((u1, u2) -> u1.compareTo(u2))
                .orElseThrow(() -> new BookException("At the moment, no users have rented books"));

        List<UsersEntity> usersRentedMax = users.stream()
                .filter(u -> u.getRented_more() == rentedMax)
                .collect(Collectors.toList());

        List<UserRentedMoreResponseDTO> userRentedMoreResponseDTOS = usersRentedMax.stream()
                .map(userRentedMoreResponseMapper::toDTO)
                .collect(Collectors.toList());

        return userRentedMoreResponseDTOS;

    }
}
