package com.example.tenisuj.security;
import com.example.tenisuj.Exception.CustomHttpException;
import com.example.tenisuj.model.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
//created
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { CustomHttpException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto>handleException(CustomHttpException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));
    }
}