package br.com.dubacchiega.library.users.entities;

import br.com.dubacchiega.library.books.entities.BooksEntity;
import br.com.dubacchiega.library.users.entities.DTO.UserRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "register_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [name] não pode ser nulo")
    private String name;

    @NotBlank(message = "O campo [username] não pode ser nulo")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Length(min = 8, max = 100, message = "A senha deve conter entre 8 a 100 caractéres")
    @NotBlank(message = "O campo [password] não pode ser nulo")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Integer rented_more = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rented_books",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id"))
    private List<BooksEntity> books = new ArrayList<>();

    public UsersEntity(UserRequestDTO user){
        this.name = user.name();
        this.username = user.username();
        this.email = user.email();
        this.password = user.password();
    }

    public void addBooks(BooksEntity booksEntity){
        books.add(booksEntity);
    }

}
