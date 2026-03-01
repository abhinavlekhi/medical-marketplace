package com.example.marketplace.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateUsernameException(DuplicateUsernameException ex,
                                                                             HttpServletRequest request) {
        List<String> messages = List.of(ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                400,
                "Bad Request",
                messages,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException mex,
                                                                       HttpServletRequest request) {
        List<String> messages = mex.getBindingResult().getFieldErrors()
                .stream().map(error -> error.getDefaultMessage())
                .toList();
        ErrorResponseDTO error = new ErrorResponseDTO(
                400,
                "Bad Request",
                messages,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidcredentialsException(InvalidCredentialsException ex,
                                                                              HttpServletRequest request) {

        List<String> messages = List.of(ex.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(
                401,
                "Unauthorized",
                messages,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
