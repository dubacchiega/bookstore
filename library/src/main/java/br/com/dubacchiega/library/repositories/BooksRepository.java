package br.com.dubacchiega.library.repositories;

import br.com.dubacchiega.library.entities.books.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BooksEntity, Long> {
}
