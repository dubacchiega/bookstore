package br.com.dubacchiega.library.users.entities.DTO;

import br.com.dubacchiega.library.users.entities.enums.UserRole;

public record UserRequestDTO(String name, String email, String username, String password, UserRole role) {
}
