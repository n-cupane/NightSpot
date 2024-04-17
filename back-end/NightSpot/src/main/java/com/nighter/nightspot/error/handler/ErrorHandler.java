package com.nighter.nightspot.error.handler;

import com.nighter.nightspot.dto.validation.ValidationErrorMessageDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ErrorHandler {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String,String>> noResultHandler(NoResultException ex, WebRequest request) {
        Map <String,String> responseBody = new HashMap<>();
        responseBody.put("timeStamp", LocalDateTime.now().toString());
        responseBody.put("error", HttpStatus.BAD_REQUEST.name());
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", request.getDescription(false));
        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorMessageDTO> handleValidation(MethodArgumentNotValidException e, WebRequest request) {
        ValidationErrorMessageDTO errorMessage = new ValidationErrorMessageDTO();
        errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setStatusString(HttpStatus.BAD_REQUEST.name());
        errorMessage.setTargetUrl(request.getDescription(false));
        errorMessage.setFieldErrors(
                e.getFieldErrors()
                        .stream()
                        .collect(
                                Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage)
                        )
        );
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
