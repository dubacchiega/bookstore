package br.com.dubacchiega.library.books.controllers;

import br.com.dubacchiega.library.books.entities.DTO.BooksRequestDTO;
import br.com.dubacchiega.library.books.entities.DTO.BooksResponseDTO;
import br.com.dubacchiega.library.books.entities.DTO.RankBooksDTO;
import br.com.dubacchiega.library.books.services.BookRankService;
import br.com.dubacchiega.library.books.services.BookRegistrationService;
import br.com.dubacchiega.library.exceptions.BookException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRegistrationService bookRegistrationService;

    @Autowired
    BookRankService bookRankService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody BooksRequestDTO booksRequestDTO){
        try{
            BooksResponseDTO book = bookRegistrationService.register(booksRequestDTO);
            return ResponseEntity.ok().body(book);
        }catch (BookException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list/all")
    public ResponseEntity<?> listAllBooks(){
        try {
            List<BooksResponseDTO> booksResponseDTOList = bookRegistrationService.listAllBooks();
            return ResponseEntity.ok().body(booksResponseDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/list/available")
    public ResponseEntity<?> listAvailableBooks(){
        try {
            List<BooksResponseDTO> booksResponseDTOList = bookRegistrationService.listAvailableBooks();
            return ResponseEntity.ok().body(booksResponseDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/list/rank")
    public ResponseEntity<?> listMostRentedRank(){
        try {
            List<RankBooksDTO> rankBooksDTOS = bookRankService.bookRank();
            return ResponseEntity.ok().body(rankBooksDTOS);
        }catch (BookException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
