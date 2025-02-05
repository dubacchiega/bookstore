package br.com.dubacchiega.library.users.controllers;

import br.com.dubacchiega.library.books.entities.DTO.RentBookDTO;
import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.DTO.UserRentBookDTO;
import br.com.dubacchiega.library.users.entities.DTO.UserRequestDTO;
import br.com.dubacchiega.library.users.entities.DTO.UserResponseDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.services.ListUserBook;
import br.com.dubacchiega.library.users.services.RentBookService;
import br.com.dubacchiega.library.users.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    RentBookService rentBookService;

    @Autowired
    ListUserBook listUserBook;

    @PostMapping("/register")
    public ResponseEntity<Object> registration(@RequestBody UserRequestDTO userRequestDTO){
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
    public ResponseEntity<Object> rent(@PathVariable UUID id, @RequestBody RentBookDTO rentBookDTO){
        try {
            UserRentBookDTO userRentBookDTO = rentBookService.rentBook(id, rentBookDTO);
            return ResponseEntity.ok().body(userRentBookDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/listBook/{id}")
    public ResponseEntity<Object> listUserBook(@PathVariable UUID id){
        try {
            UsersEntity usersEntity = listUserBook.listUserBook(id);
            return ResponseEntity.ok().body(usersEntity);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
