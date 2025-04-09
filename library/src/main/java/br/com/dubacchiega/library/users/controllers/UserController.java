package br.com.dubacchiega.library.users.controllers;

import br.com.dubacchiega.library.books.entities.DTO.RentBookDTO;
import br.com.dubacchiega.library.users.entities.DTO.*;
import br.com.dubacchiega.library.users.services.ListUserBookService;
import br.com.dubacchiega.library.users.services.RentBookService;
import br.com.dubacchiega.library.users.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final RentBookService rentBookService;
    private final ListUserBookService listUserBookService;

    // TODO: Refatorar a mensagem de resposta, usando MAP

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registration(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userRegistrationService.userRegistration(userRequestDTO);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "User registered successfully");
        response.put("data: ", userResponseDTO);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/rent/{id}")
    public ResponseEntity<Map<String, Object>> rent(@PathVariable UUID id, @RequestBody RentBookDTO rentBookDTO){
        UserRentBookDTO userRentBookDTO = rentBookService.rentBook(id, rentBookDTO);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Book rented successfully");
        response.put("data: ", userRentBookDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/listBook/{id}")
    public ResponseEntity<Map<String, Object>> listUserBook(@PathVariable UUID id){
        UserRentBookResponseDTO userRentBookResponseDTO = listUserBookService.listUserBook(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Successfully listed books");
        response.put("data: ", userRentBookResponseDTO);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<Map<String, Object>> returnBooks(@PathVariable UUID id, @RequestBody RentBookDTO rentBookDTO){
        UserRentBookResponseDTO userRentBookResponseDTO = rentBookService.returnBook(id, rentBookDTO);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Book returned successfully");
        response.put("data: ", userRentBookResponseDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/list/mostRented")
    public ResponseEntity<Map<String, Object>> mostRented(){
        List<UserRentedMoreResponseDTO> userRentedMoreResponseDTO = rentBookService.userRentedMostBook();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Successfully listed most rented books");
        response.put("data: ", userRentedMoreResponseDTO);
        return ResponseEntity.ok().body(response);
    }
}
