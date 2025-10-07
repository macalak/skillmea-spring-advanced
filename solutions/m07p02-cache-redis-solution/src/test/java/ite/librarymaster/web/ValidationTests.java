package ite.librarymaster.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ite.librarymaster.model.PasswordHolder;
import ite.librarymaster.validation.PasswordValidator;

@SpringBootTest
public class ValidationTests {

    @Test
    public void validatePasswordTest(){
        PasswordHolder password = new PasswordHolder("password", "notmatched");
        // Immediate validation example. Not bounded to web or other tier.
        // Validator factory implementation using Validator.forInstanceOf
        Validator passwordValidator = Validator.forInstanceOf(PasswordHolder.class, (ph, errors) -> {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
            if (!Objects.equals(ph.getPassword(), ph.getPasswordConfirmation())) {
                errors.rejectValue("passwordConfirmation",
                    "PasswordValidator.passwordResetForm.password",
                    "password and confirm password must be same.");
            }
          });
        
        // Immediate validation invocations
        assertTrue(passwordValidator.validateObject(password).hasErrors());  
        // Print out validation errors
        passwordValidator.validateObject(password).getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .forEach(System.out::println);

        password = new PasswordHolder("password", "password");
        assertFalse(passwordValidator.validateObject(password).hasErrors());  

    }

    @Test
    public void validatePasswordUsingBinder(){
        PasswordHolder password = new PasswordHolder(null, "notmatched");
        DataBinder dataBinder = new DataBinder(password);
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("ValidationMessages");
        dataBinder.addValidators(new PasswordValidator());
        dataBinder.validate();
        dataBinder.getBindingResult().getAllErrors().stream()
                    .forEach(e -> System.out.println(messageSource.getMessage(e, Locale.US)));
        
    }
    
}
