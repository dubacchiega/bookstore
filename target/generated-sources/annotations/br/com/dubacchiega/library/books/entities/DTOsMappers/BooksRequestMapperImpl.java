package br.com.dubacchiega.library.books.entities.DTOsMappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-04T19:45:05-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class BooksRequestMapperImpl implements BooksRequestMapper {

    @Override
    public BooksEntity toEntity(BooksRequestDTO booksRequestDTO) {
        if ( booksRequestDTO == null ) {
            return null;
        }

        BooksEntity booksEntity = new BooksEntity();

        booksEntity.setTitle( booksRequestDTO.title() );
        booksEntity.setAuthor( booksRequestDTO.author() );
        booksEntity.setStock( booksRequestDTO.stock() );

        return booksEntity;
    }

    @Override
    public BooksRequestDTO toDTO(BooksEntity booksEntity) {
        if ( booksEntity == null ) {
            return null;
        }

        String title = null;
        String author = null;
        Integer stock = null;

        title = booksEntity.getTitle();
        author = booksEntity.getAuthor();
        stock = booksEntity.getStock();

        BooksRequestDTO booksRequestDTO = new BooksRequestDTO( title, author, stock );

        return booksRequestDTO;
    }
}
