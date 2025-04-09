package br.com.dubacchiega.library.books.services;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.books.entities.DTO.RankBooksDTO;
import br.com.dubacchiega.library.books.repositories.BooksRepository;
import br.com.dubacchiega.library.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRankService {

    @Autowired
    private BooksRepository booksRepository;

    public List<RankBooksDTO> bookRank(){

        List<BooksEntity> books = booksRepository.findAll();
        if (books.isEmpty()){
            throw new BookNotFoundException("No books in the rank");
        }
        ArrayList<BooksEntity> booksToOrder = new ArrayList<>(books);

        List<BooksEntity> orderedBooks = booksToOrder.stream()
                .filter((booksEntity) -> {
                    return booksEntity.getMostRentedRank() != null;})
                .sorted((b1,b2) -> Integer.compare(b2.getMostRentedRank(), b1.getMostRentedRank()))
                .collect(Collectors.toList());

        List<RankBooksDTO> result = new ArrayList<>();
        for (BooksEntity book : orderedBooks){
            result.add(new RankBooksDTO(book.getTitle(), book.getAuthor(), book.getMostRentedRank()));
        }
        return result;
    }
}
