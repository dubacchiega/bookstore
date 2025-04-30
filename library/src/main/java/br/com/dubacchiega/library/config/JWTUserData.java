package br.com.dubacchiega.library.config;

import br.com.dubacchiega.library.users.entities.enums.UserRole;
import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserData(UUID id, String name, String email, String username, UserRole role) {
}
