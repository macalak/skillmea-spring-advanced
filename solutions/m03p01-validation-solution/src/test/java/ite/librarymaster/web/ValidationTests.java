package ite.librarymaster.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import ite.librarymaster.api.AuthorDTO;
import ite.librarymaster.api.BookDTO;
import ite.librarymaster.api.MediumAvailability;
import ite.librarymaster.model.CalendarEvent;
import ite.librarymaster.service.DateService;
import ite.librarymaster.validation.CreateBookGroup;
import ite.librarymaster.validation.UpdateBookGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    jakarta.validation.Validator validator;

    @Autowired
    DateService dateService;


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
        //messageSource.setBasename("ValidationMessages");
        dataBinder.addValidators(new PasswordValidator());
        dataBinder.validate();
        dataBinder.getBindingResult().getAllErrors().stream()
                    .forEach(e -> System.out.println(messageSource.getMessage(e, Locale.US)));
        
    }

    @Test
    public void groupValidationTest(){
        BookDTO testedBean = new BookDTO();
        // Simulate validation during creation
        // Focus on catId (must be valid) and title (must be valid)
        // Validate with CreateBookGroup and expect validation PASSED
        testedBean.catId = "LM-000001";
        testedBean.title = "I Robot";
        Set<ConstraintViolation<BookDTO>> result = validator.validate(testedBean, CreateBookGroup.class);
        assertTrue(result.isEmpty());

        // Simulate validation during update
        // Focus on catId (not part of UpdateBookGroup) and title (must be valid)
        // Validate with UpdateBookGroup and expect validation PASSED
        testedBean.catId = null;
        result = validator.validate(testedBean, UpdateBookGroup.class);
        assertTrue(result.isEmpty());

        // Now validate with CreateBookGroup again
        // catId is not set
        // We expect there are violations
        result = validator.validate(testedBean, CreateBookGroup.class);
        assertFalse(result.isEmpty());
        result.forEach(violation -> {
            System.out.println(violation.getMessage());
        });
    }

    @Test
    public void dateServiceTest(){
        Date start = Date.from(Instant.now());
        Date end = Date.from(Instant.now().minus(10, ChronoUnit.HOURS));
        dateService.someFunction(null, start, end);
    }

    @Test
    public void objectGraphValidationTest() {
        BookDTO testedBean = new BookDTO();
        AuthorDTO authorDTO = new AuthorDTO();
        // Simulate validation during creation
        // Focus on catId (must be valid) and title (must be valid)
        // Validate with CreateBookGroup and expect validation PASSED
        testedBean.catId = "LM-000001";
        testedBean.title = "I Robot";
        testedBean.availability = MediumAvailability.Available;
        authorDTO.name = "George";
        authorDTO.middleName = "R. R.";
        authorDTO.surname = "Martin";
        authorDTO.email = "macalak@gmail.com";
        testedBean.author = authorDTO;
        Set<ConstraintViolation<BookDTO>> result = validator.validate(testedBean, CreateBookGroup.class, Default.class);
        result.forEach(violation -> {
            System.out.println(violation.getMessage());
        });
        assertTrue(result.isEmpty());
    }

    @Test
    public void verifyValidCalendarEvent(){
            CalendarEvent calendarEvent = new CalendarEvent(Date.from(Instant.now()),
                                                            Date.from(Instant.now().plus(10, ChronoUnit.DAYS)) );
            Set<ConstraintViolation<CalendarEvent>> result = validator.validate(calendarEvent);
            assertTrue(result.isEmpty());
    }
}
