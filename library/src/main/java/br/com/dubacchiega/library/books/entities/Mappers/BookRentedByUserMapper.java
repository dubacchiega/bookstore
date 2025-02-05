package br.com.dubacchiega.library.books.entities.Mappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.BookRentedByUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookRentedByUserMapper {

    BookRentedByUserMapper INSTANCE = Mappers.getMapper(BookRentedByUserMapper.class);

    BooksEntity toEntity(BookRentedByUserDTO bookRentedByUserDTO);
    BookRentedByUserDTO toDTO(BooksEntity booksEntity);
}
