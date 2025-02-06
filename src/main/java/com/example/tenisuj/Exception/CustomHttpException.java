package com.example.tenisuj.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
//created
public class CustomHttpException extends ResponseStatusException {

    private final HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public CustomHttpException(String message, HttpStatus status) {
        super(status, message);
        this.status = status;

    }
}
