package ite.librarymaster.api;

import ite.librarymaster.service.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionMapper {
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public String handleItemNotFoundException(ItemNotFoundException e){
        return e.getMessage();
    }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(ConstraintViolationException.class)
   public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
       Map<String, String> errors = new HashMap<>();
       for (ConstraintViolation violation: ex.getConstraintViolations()){
           errors.put(violation.getPropertyPath().toString(), violation.getMessage());
       }
       return errors;
   }
}
