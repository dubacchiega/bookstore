package br.com.dubacchiega.library.books.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.BooksRequestDTO;
import br.com.dubacchiega.library.books.entities.BooksResponseDTO;
import br.com.dubacchiega.library.books.repositories.BooksRepository;
import br.com.dubacchiega.library.exceptions.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookRegistrationService {

    @Autowired
    private BooksRepository booksRepository;

    public BooksResponseDTO register(BooksRequestDTO booksRequestDTO){
        booksRepository.findByTitle(booksRequestDTO.title()).ifPresent(
                books -> {
                    throw new BookException("Book already registered");
                }
        );

        BooksEntity booksEntity = booksRepository.save(new BooksEntity(booksRequestDTO));
        BooksResponseDTO booksResponseDTO = new BooksResponseDTO(booksEntity.getTitle(), booksEntity.getAuthor(), booksEntity.getStock(), booksEntity.getAvailable());
        return booksResponseDTO;
    }

    public List<BooksResponseDTO> listAllBooks(){
        List<BooksEntity> books = booksRepository.findAll();
        if(books.isEmpty()){
            throw new BookException("No books registered");
        }else {
            List<BooksResponseDTO> booksResponse = new ArrayList<>();
            for (BooksEntity book : books){
                BooksResponseDTO bookResp = new BooksResponseDTO(
                        book.getTitle(),
                        book.getAuthor(),
                        book.getStock(),
                        book.getAvailable()
                );
                booksResponse.add(bookResp);
            }
            return booksResponse;
        }
    }

    public List<BooksResponseDTO> listAvailableBooks(){
        List<BooksResponseDTO> allbooks = listAllBooks();
        List<BooksResponseDTO> availableBooks = new ArrayList<>();
        for (BooksResponseDTO book : allbooks){
            if (book.available()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}
