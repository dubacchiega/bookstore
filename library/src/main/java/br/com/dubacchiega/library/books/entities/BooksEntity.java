package br.com.dubacchiega.library.books.entities;

import br.com.dubacchiega.library.books.entities.DTO.BooksRequestDTO;
import br.com.dubacchiega.library.users.entities.UsersEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [title] deve conter um título")
    private String title;

    @NotBlank(message = "O campo [author] deve conter um autor")
    private String author;

    private Integer stock;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "books")
    @JsonIgnore
    @ToString.Exclude
    private List<UsersEntity> users = new ArrayList<>();

    @Transient // Indica que este campo não será persistido no banco de dados
    // como estoque a disponibilidade é variada de stock, então eu crio um método que retorna true se o estoque for diferente de null e maior que 0, e falso caso contrário.
    // assim eu evito que crie outro campo a mais.
    public Boolean getAvailable() {
        return this.stock != null && this.stock > 0;
    }

    @Column(name = "most_rented_rank")
    private Integer mostRentedRank = 0;


    public BooksEntity(BooksRequestDTO book){
        this.title = book.title();
        this.author = book.author();
        this.stock = book.stock();
    }
}
