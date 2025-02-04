package br.com.dubacchiega.library.books.repositories;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BooksRepository extends JpaRepository<BooksEntity, UUID> {

    Optional<BooksEntity> findByTitle(String title);
}
