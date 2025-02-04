package br.com.dubacchiega.library.books.entities.DTOsMappers;

public record BooksResponseDTO(String title, String author, Integer stock, boolean available) {
}
