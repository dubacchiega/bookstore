package br.com.dubacchiega.library.books.entities.DTOsMappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BooksRequestMapper {

    BooksRequestMapper INSTANCE = Mappers.getMapper(BooksRequestMapper.class);

    BooksEntity toEntity(BooksRequestDTO booksRequestDTO);
    BooksRequestDTO toDTO(BooksEntity booksEntity);
}
