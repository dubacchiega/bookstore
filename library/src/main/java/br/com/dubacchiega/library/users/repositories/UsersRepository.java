package br.com.dubacchiega.library.users.repositories;

import br.com.dubacchiega.library.users.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {

    Optional<UserDetails> findByEmail(String email);
    boolean existsByIdAndBooksId(UUID id, UUID booksId);


}
