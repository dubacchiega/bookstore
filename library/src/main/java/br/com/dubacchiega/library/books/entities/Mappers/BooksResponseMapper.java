package br.com.dubacchiega.library.books.entities.Mappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.BooksResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BooksResponseMapper {

    BooksResponseMapper INSTANCE = Mappers.getMapper(BooksResponseMapper.class);

    BooksEntity toEntity(BooksResponseDTO booksResponseDTO);
    BooksResponseDTO toDTO(BooksEntity booksEntity);
}
