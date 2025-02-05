package br.com.dubacchiega.library.books.entities.DTO;

public record BooksResponseDTO(String title, String author, Integer stock, boolean available) {
}
