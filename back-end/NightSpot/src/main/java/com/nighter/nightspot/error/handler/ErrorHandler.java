package com.nighter.nightspot.error.handler;

import com.nighter.nightspot.dto.validation.ValidationErrorMessageDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
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

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class, ExpiredJwtException.class,
            MalformedJwtException.class, SignatureException.class})
    public ProblemDetail handleJwtError(Exception e) {

        ProblemDetail p = null;

        if (e instanceof UsernameNotFoundException) {
            p = ProblemDetail.forStatus(HttpStatus.FORBIDDEN.value());
            p.setType(URI.create("BAD_CREDENTIALS"));
            p.setTitle("Username not found");
            p.setDetail(e.getMessage());
        }

        else if (e instanceof BadCredentialsException) {
            p = ProblemDetail.forStatus(HttpStatus.FORBIDDEN.value());
            p.setType(URI.create("BAD_CREDENTIALS"));
            p.setTitle("Wrong password");
            p.setDetail(e.getMessage());
        }

        else if (e instanceof ExpiredJwtException) {
            p = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED.value());
            p.setType(URI.create("TOKEN_ERROR"));
            p.setTitle("Expired token");
            p.setDetail(e.getMessage());
        }

        else if (e instanceof MalformedJwtException) {
            p = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED.value());
            p.setType(URI.create("TOKEN_ERROR"));
            p.setTitle("Malformed token");
            p.setDetail(e.getMessage());
        }

        else if (e instanceof SignatureException) {
            p = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED.value());
            p.setType(URI.create("TOKEN_ERROR"));
            p.setTitle("Wrong signature");
            p.setDetail(e.getMessage());
        }

        return p;

    }
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        e.printStackTrace();
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
