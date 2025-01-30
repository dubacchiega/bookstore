package br.com.dubacchiega.library.books.entities;

public record BooksResponseDTO(String title, String author, Integer stock, boolean available) {
}
