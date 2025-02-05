package br.com.dubacchiega.library.books.entities.Mappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.BooksRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BooksRequestMapper {

    BooksRequestMapper INSTANCE = Mappers.getMapper(BooksRequestMapper.class);

    BooksEntity toEntity(BooksRequestDTO booksRequestDTO);
    BooksRequestDTO toDTO(BooksEntity booksEntity);
}
