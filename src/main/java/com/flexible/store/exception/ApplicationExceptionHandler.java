package com.flexible.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = {WebApiException.class})
    public ResponseEntity<Object> handleApiRequestException(WebApiException webApiException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .message(webApiException.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String defaultMessage = objectError.getDefaultMessage();
            errors.put(fieldName, defaultMessage);
        }));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
