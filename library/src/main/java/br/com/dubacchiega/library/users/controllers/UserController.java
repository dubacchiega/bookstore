package br.com.dubacchiega.library.users.controllers;

import br.com.dubacchiega.library.exceptions.UserException;
import br.com.dubacchiega.library.users.entities.UserRequestDTO;
import br.com.dubacchiega.library.users.entities.UserResponseDTO;
import br.com.dubacchiega.library.users.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<Object> registration(@RequestBody UserRequestDTO userRequestDTO){
        try {
            UserResponseDTO userResponseDTO = userRegistrationService.userRegistration(userRequestDTO);
            return ResponseEntity.ok().body(userResponseDTO);
        }catch (UserException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
