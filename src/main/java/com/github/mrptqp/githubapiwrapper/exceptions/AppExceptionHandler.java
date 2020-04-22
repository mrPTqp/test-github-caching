package com.github.mrptqp.githubapiwrapper.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {

        return new ResponseEntity<>(
                getErrorMessage(ex, HttpStatus.CONFLICT),
                new HttpHeaders(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<>(
                getErrorMessage(ex, HttpStatus.NOT_FOUND),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {

        return new ResponseEntity<>(
                getErrorMessage(ex, HttpStatus.BAD_REQUEST),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {

        return new ResponseEntity<>(
                getErrorMessage(ex, HttpStatus.UNAUTHORIZED),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED
        );
    }

    private ErrorMessage getErrorMessage(Exception ex, HttpStatus status) {
        String errorMessageDescription = ex.getLocalizedMessage();

        if (errorMessageDescription == null) {
            errorMessageDescription = ex.toString();
        }

        LocalDateTime timestamp = LocalDateTime.now();

        return new ErrorMessage(
                timestamp,
                errorMessageDescription,
                status.value(),
                status.getReasonPhrase()
        );
    }
}
