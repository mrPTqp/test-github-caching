package com.learning.spring.githubapiwrapper.exceptions;


public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
