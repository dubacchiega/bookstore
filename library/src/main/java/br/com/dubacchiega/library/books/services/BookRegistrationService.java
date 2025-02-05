package br.com.dubacchiega.library.books.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.BooksRequestDTO;
import br.com.dubacchiega.library.books.entities.Mappers.BooksRequestMapper;
import br.com.dubacchiega.library.books.entities.DTO.BooksResponseDTO;
import br.com.dubacchiega.library.books.entities.Mappers.BooksResponseMapper;
import br.com.dubacchiega.library.books.repositories.BooksRepository;
import br.com.dubacchiega.library.exceptions.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRegistrationService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private BooksRequestMapper booksRequestMapper;

    @Autowired
    private BooksResponseMapper booksResponseMapper;

    public BooksResponseDTO register(BooksRequestDTO booksRequestDTO){
        booksRepository.findByTitle(booksRequestDTO.title()).ifPresent(
                books -> {
                    throw new BookException("Book already registered");
                }
        );

        BooksEntity booksEntity = booksRequestMapper.toEntity(booksRequestDTO);
        booksRepository.save(booksEntity);
        BooksResponseDTO booksResponseDTO = booksResponseMapper.toDTO(booksEntity);
        return booksResponseDTO;
    }

    public List<BooksResponseDTO> listAllBooks(){
        List<BooksEntity> books = booksRepository.findAll();
        if(books.isEmpty()){
            throw new BookException("No books registered");
        }else {
            return books.stream()
                    .map(booksResponseMapper::toDTO)
                    .collect(Collectors.toList());
        }
    }

    public List<BooksResponseDTO> listAvailableBooks(){
        List<BooksResponseDTO> allbooks = listAllBooks();
        return allbooks.stream()
                .filter(BooksResponseDTO::available)
                .collect(Collectors.toList());
    }
}
