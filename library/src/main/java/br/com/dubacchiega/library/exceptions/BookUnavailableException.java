package br.com.dubacchiega.library.exceptions;

public class BookUnavailableException extends RuntimeException{
    public BookUnavailableException(String message){
        super(message);
    }
}
