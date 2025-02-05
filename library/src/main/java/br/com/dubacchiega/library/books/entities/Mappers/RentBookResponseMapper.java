package br.com.dubacchiega.library.books.entities.Mappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.RentBookResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentBookResponseMapper {

    RentBookResponseMapper INSTANCE = Mappers.getMapper(RentBookResponseMapper.class);

    BooksEntity toEntity(RentBookResponseDTO rentBookResponseDTO);
    RentBookResponseDTO toDTO(BooksEntity booksEntity);
}
