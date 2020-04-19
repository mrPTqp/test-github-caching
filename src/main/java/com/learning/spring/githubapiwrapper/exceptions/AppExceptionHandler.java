package com.learning.spring.githubapiwrapper.exceptions;

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

        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessage errorMessage = this.getErrorMessage(ex, status);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage errorMessage = this.getErrorMessage(ex, status);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = this.getErrorMessage(ex, status);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorMessage errorMessage = this.getErrorMessage(ex, status);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
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
