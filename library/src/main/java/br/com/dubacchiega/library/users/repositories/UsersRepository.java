package br.com.dubacchiega.library.users.repositories;

import br.com.dubacchiega.library.users.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByEmail(String email);
}
