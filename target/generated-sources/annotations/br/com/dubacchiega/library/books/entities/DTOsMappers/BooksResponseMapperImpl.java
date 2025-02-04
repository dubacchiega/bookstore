package br.com.dubacchiega.library.books.entities.DTOsMappers;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-04T19:45:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class BooksResponseMapperImpl implements BooksResponseMapper {

    @Override
    public BooksEntity toEntity(BooksResponseDTO booksResponseDTO) {
        if ( booksResponseDTO == null ) {
            return null;
        }

        BooksEntity booksEntity = new BooksEntity();

        booksEntity.setTitle( booksResponseDTO.title() );
        booksEntity.setAuthor( booksResponseDTO.author() );
        booksEntity.setStock( booksResponseDTO.stock() );

        return booksEntity;
    }

    @Override
    public BooksResponseDTO toDTO(BooksEntity booksEntity) {
        if ( booksEntity == null ) {
            return null;
        }

        String title = null;
        String author = null;
        Integer stock = null;
        boolean available = false;

        title = booksEntity.getTitle();
        author = booksEntity.getAuthor();
        stock = booksEntity.getStock();
        if ( booksEntity.getAvailable() != null ) {
            available = booksEntity.getAvailable();
        }

        BooksResponseDTO booksResponseDTO = new BooksResponseDTO( title, author, stock, available );

        return booksResponseDTO;
    }
}
