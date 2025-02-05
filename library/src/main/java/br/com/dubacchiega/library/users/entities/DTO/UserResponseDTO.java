package br.com.dubacchiega.library.users.entities.DTO;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponseDTO(String name, String username, String email, LocalDateTime createdAt) {
}
