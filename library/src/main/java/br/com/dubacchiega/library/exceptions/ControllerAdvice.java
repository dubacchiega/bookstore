package br.com.dubacchiega.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UserDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserDuplicateException(UserDuplicateException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleBadCredentialsException(BadCredentialsException e) {
        return "User/password incorrect";
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBookNotFoundException(BookNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserBookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserBookException(UserBookException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BookUnavailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBookUnavailableException(BookUnavailableException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBookException(BookException e) {
        return e.getMessage();
    }



}
