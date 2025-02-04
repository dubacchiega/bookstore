package br.com.dubacchiega.library.books.entities.DTOsMappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BooksResponseMapper {

    BooksResponseMapper INSTANCE = Mappers.getMapper(BooksResponseMapper.class);

    BooksEntity toEntity(BooksResponseDTO booksResponseDTO);
    BooksResponseDTO toDTO(BooksEntity booksEntity);
}
