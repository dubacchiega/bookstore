package br.com.dubacchiega.library.users.controllers;

import br.com.dubacchiega.library.config.TokenService;
import br.com.dubacchiega.library.users.entities.DTO.LoginRequestDTO;
import br.com.dubacchiega.library.users.entities.DTO.LoginResponseDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import br.com.dubacchiega.library.users.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRegistrationService userRegistrationService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UsersEntity user = (UsersEntity) authentication.getPrincipal();
        String token = tokenService.generateToken(user);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "User logged in successfully");
        response.put("data: ", loginResponseDTO);

        return ResponseEntity.ok(response);

    }

}
