package br.com.dubacchiega.library.repositories;

import br.com.dubacchiega.library.entities.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
}
