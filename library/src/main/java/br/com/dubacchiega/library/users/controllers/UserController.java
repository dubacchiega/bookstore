package br.com.dubacchiega.library.users.controllers;

import br.com.dubacchiega.library.books.entities.DTO.RentBookDTO;
import br.com.dubacchiega.library.exceptions.BookException;
import br.com.dubacchiega.library.exceptions.UserBookException;
import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.DTO.*;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.services.ListUserBookService;
import br.com.dubacchiega.library.users.services.RentBookService;
import br.com.dubacchiega.library.users.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    RentBookService rentBookService;

    @Autowired
    ListUserBookService listUserBookService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody UserRequestDTO userRequestDTO){
        try {
            UserResponseDTO userResponseDTO = userRegistrationService.userRegistration(userRequestDTO);
            return ResponseEntity.ok().body(userResponseDTO);
        }catch (UserException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/rent/{id}")
    public ResponseEntity<?> rent(@PathVariable UUID id, @RequestBody RentBookDTO rentBookDTO){
        try {
            UserRentBookDTO userRentBookDTO = rentBookService.rentBook(id, rentBookDTO);
            return ResponseEntity.ok().body(userRentBookDTO);
        }catch (BookException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (UserBookException e){
            return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    @GetMapping("/listBook/{id}")
    public ResponseEntity<?> listUserBook(@PathVariable UUID id){
        try {
            UserRentBookResponseDTO userRentBookResponseDTO = listUserBookService.listUserBook(id);
            return ResponseEntity.ok().body(userRentBookResponseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<?> returnBooks(@PathVariable UUID id, @RequestBody RentBookDTO rentBookDTO){
        try {
            UserRentBookResponseDTO userRentBookResponseDTO = rentBookService.returnBook(id, rentBookDTO);
            return ResponseEntity.ok().body(userRentBookResponseDTO);
        }catch (BookException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (UserBookException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list/mostRented")
    public ResponseEntity<?> mostRented(){
        try {
            List<UserRentedMoreResponseDTO> userRentedMoreResponseDTO = rentBookService.userRentedMostBook();
            return ResponseEntity.ok().body(userRentedMoreResponseDTO);
        }catch (UserException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
