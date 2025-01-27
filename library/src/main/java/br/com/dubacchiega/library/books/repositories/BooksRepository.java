package br.com.dubacchiega.library.books.repositories;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BooksEntity, Long> {
}
