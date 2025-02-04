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
public class RentBookResponseMapperImpl implements RentBookResponseMapper {

    @Override
    public BooksEntity toEntity(RentBookResponseDTO rentBookResponseDTO) {
        if ( rentBookResponseDTO == null ) {
            return null;
        }

        BooksEntity booksEntity = new BooksEntity();

        booksEntity.setTitle( rentBookResponseDTO.title() );
        booksEntity.setAuthor( rentBookResponseDTO.author() );
        booksEntity.setStock( rentBookResponseDTO.stock() );

        return booksEntity;
    }

    @Override
    public RentBookResponseDTO toDTO(BooksEntity booksEntity) {
        if ( booksEntity == null ) {
            return null;
        }

        String title = null;
        String author = null;
        Integer stock = null;

        title = booksEntity.getTitle();
        author = booksEntity.getAuthor();
        stock = booksEntity.getStock();

        RentBookResponseDTO rentBookResponseDTO = new RentBookResponseDTO( title, author, stock );

        return rentBookResponseDTO;
    }
}
