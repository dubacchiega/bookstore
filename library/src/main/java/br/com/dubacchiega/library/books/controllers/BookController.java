package br.com.dubacchiega.library.books.controllers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.BooksRequestDTO;
import br.com.dubacchiega.library.books.services.BookRegistrationService;
import br.com.dubacchiega.library.exceptions.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRegistrationService bookRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody BooksRequestDTO booksRequestDTO){
        try{
            BooksEntity book = bookRegistrationService.register(booksRequestDTO);
            return ResponseEntity.ok().body(book);
        }catch (BookException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
