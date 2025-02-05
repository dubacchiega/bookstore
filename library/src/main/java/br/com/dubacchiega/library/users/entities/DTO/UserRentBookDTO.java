package br.com.dubacchiega.library.users.entities.DTO;

import br.com.dubacchiega.library.books.entities.DTO.RentBookResponseDTO;



public record UserRentBookDTO(String name, String username, String email, RentBookResponseDTO books) {
}
