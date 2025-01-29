package br.com.dubacchiega.library.books.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.BooksRequestDTO;
import br.com.dubacchiega.library.books.repositories.BooksRepository;
import br.com.dubacchiega.library.exceptions.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRegistrationService {

    @Autowired
    private BooksRepository booksRepository;

    public BooksEntity register(BooksRequestDTO booksRequestDTO){
        booksRepository.findByTitle(booksRequestDTO.title()).ifPresent(
                books -> {
                    throw new BookException("Book already registered");
                }
        );

        BooksEntity booksEntity = booksRepository.save(new BooksEntity(booksRequestDTO));
        return booksEntity;
    }
}
