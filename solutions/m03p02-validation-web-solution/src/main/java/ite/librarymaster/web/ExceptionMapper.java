package ite.librarymaster.web;

import ite.librarymaster.service.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionMapper {
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public ModelAndView handleItemNotFoundException(ItemNotFoundException e){
        ModelAndView model = new ModelAndView();
        model.addObject("message", e.getMessage());
        model.setViewName("error/404");
        return model;
    }
}
