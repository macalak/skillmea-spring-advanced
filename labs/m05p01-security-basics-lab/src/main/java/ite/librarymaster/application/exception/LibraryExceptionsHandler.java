package ite.librarymaster.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class LibraryExceptionsHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleTransportException(ItemNotFoundException libraryException, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(libraryException.getMessage());
    }
}
