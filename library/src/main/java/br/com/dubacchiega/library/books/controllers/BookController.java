package br.com.dubacchiega.library.books.controllers;

import br.com.dubacchiega.library.books.entities.DTO.BooksRequestDTO;
import br.com.dubacchiega.library.books.entities.DTO.BooksResponseDTO;
import br.com.dubacchiega.library.books.entities.DTO.RankBooksDTO;
import br.com.dubacchiega.library.books.services.BookRankService;
import br.com.dubacchiega.library.books.services.BookRegistrationService;
import br.com.dubacchiega.library.exceptions.BookNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookRegistrationService bookRegistrationService;
    private final BookRankService bookRankService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody BooksRequestDTO booksRequestDTO){
        BooksResponseDTO book = bookRegistrationService.register(booksRequestDTO);
        Map<String, Object> respose = new LinkedHashMap<>();
        respose.put("message: ", "Book registered successfully");
        respose.put("data: ", book);
        return ResponseEntity.ok().body(respose);
    }

    @GetMapping("/list/all")
    public ResponseEntity<Map<String, Object>> listAllBooks(){
        List<BooksResponseDTO> booksResponseDTOList = bookRegistrationService.listAllBooks();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Books listed successfully");
        response.put("data: ", booksResponseDTOList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/list/available")
    public ResponseEntity<Map<String, Object>> listAvailableBooks(){
        List<BooksResponseDTO> booksResponseDTOList = bookRegistrationService.listAvailableBooks();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Available books listed successfully");
        response.put("data: ", booksResponseDTOList);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/list/rank")
    public ResponseEntity<Map<String, Object>> listMostRentedRank(){
        List<RankBooksDTO> rankBooksDTOS = bookRankService.bookRank();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Most rented books listed successfully");
        response.put("data: ", rankBooksDTOS);
        return ResponseEntity.ok().body(response);
    }
}
