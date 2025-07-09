package ru.rogotovskiy.neoflexbookapi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rogotovskiy.neoflexbookapi.dto.ErrorResponse;
import ru.rogotovskiy.neoflexbookapi.exception.BookAlreadyTakenException;
import ru.rogotovskiy.neoflexbookapi.exception.BookNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(toErrorResponse(e));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBookAlreadyTakenException(BookAlreadyTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(toErrorResponse(e));
    }

    private ErrorResponse toErrorResponse(RuntimeException e) {
        return new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
    }
}
