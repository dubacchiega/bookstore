package br.com.dubacchiega.library.exceptions;

public class UserDuplicateException extends RuntimeException{

    public UserDuplicateException(String message){
        super(message);
    }
}
