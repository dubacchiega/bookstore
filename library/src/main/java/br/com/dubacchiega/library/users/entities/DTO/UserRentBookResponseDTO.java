package br.com.dubacchiega.library.users.entities.DTO;

import br.com.dubacchiega.library.books.entities.DTO.BookRentedByUserDTO;


import java.util.List;

public record UserRentBookResponseDTO(String name, String username, String email, List<BookRentedByUserDTO> books) {
}
