package br.com.dubacchiega.library.users.entities;

import br.com.dubacchiega.library.books.entities.DTOsMappers.RentBookResponseDTO;



public record UserRentBookDTO(String name, String username, String email, RentBookResponseDTO books) {
}
