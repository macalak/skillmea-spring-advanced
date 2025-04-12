package ite.librarymaster;

import ite.librarymaster.api.AuthorDTO;
import ite.librarymaster.api.BookDTO;
import ite.librarymaster.api.BookGenre;
import ite.librarymaster.api.MediumAvailability;
import ite.librarymaster.model.CalendarEvent;
import ite.librarymaster.model.PasswordHolder;
import ite.librarymaster.validation.PasswordValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ValidationUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Convenient Spring Boot Test which initializes the Spring Framework components
// You can then utilise injection of platform Validator ( Hibernate validation provider)
@SpringBootTest
public class ValidationTests {
    // Injecting validator implementation initialized upon Spring application configuration

    @Test
    public void bookDTOValidationTest(){
        BookDTO testedBean = new BookDTO();
        testedBean.title = "Game of Thrones";
        testedBean.author = "George R. R. Martin";
        testedBean.availability = MediumAvailability.Available;
        testedBean.genre = BookGenre.Fantasy;
        testedBean.publisher = "HarperCollins Publishers";
        testedBean.isbn = "9780006479888";
        testedBean.catId = "LM-122222";
    }

    @Test
    public void groupValidationTest(){
        BookDTO testedBean = new BookDTO();
        // Simulate validation during creation
        // Focus on catId (must be valid) and title (must be valid)
        // Validate with CreateBookGroup and expect validation PASSED
        testedBean.catId = "LM-000001";
        testedBean.title = "I Robot";
        //Set<ConstraintViolation<BookDTO>> result = validator.validate(testedBean, CreateBookGroup.class);
        //assertTrue(result.isEmpty());

        // Simulate validation during update
        // Focus on catId (not part of UpdateBookGroup) and title (must be valid)
        // Validate with UpdateBookGroup and expect validation PASSED
        testedBean.catId = null;
        //result = validator.validate(testedBean, UpdateBookGroup.class, CreateBookGroup.class);
        //assertTrue(result.isEmpty());

        // Now validate with CreateBookGroup again
        // catId is not set
        // We expect there are violations
//        result = validator.validate(testedBean, CreateBookGroup.class);
//        assertFalse(result.isEmpty());
//        result.forEach(violation -> {
//            System.out.println(violation.getMessage());
//        });
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
//        Set<ConstraintViolation<BookDTO>> result = validator.validate(testedBean, CreateBookGroup.class);
//        result.forEach(violation -> {
//            System.out.println(violation.getMessage());
//        });
//        assertTrue(result.isEmpty());
    }

//    @Test
//    public void verifyValidCalendarEvent(){
//        CalendarEvent calendarEvent = new CalendarEvent(Date.from(Instant.now()),
//                Date.from(Instant.now().plus(10, ChronoUnit.DAYS)) );
//        Set<ConstraintViolation<CalendarEvent>> result = validator.validate(calendarEvent);
//        assertTrue(result.isEmpty());
//    }

}
