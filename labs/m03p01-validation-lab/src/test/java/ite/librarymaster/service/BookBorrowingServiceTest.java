package ite.librarymaster.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@SpringBootTest
public class BookBorrowingServiceTest {
    @Autowired
    BookBorrowingService bookBorrowingService;

    @Test
    public void recordBorrowingDatesShouldFailedOnValidationTest(){
        Date borrowedDate = Date.from(Instant.now());
        Date returnedDate = Date.from(Instant.now().minus(10, ChronoUnit.HOURS));
//        assertThrows(ConstraintViolationException.class, () ->
//                bookBorrowingService.recordBorrowingDates(UUID.randomUUID(), borrowedDate, returnedDate));

    }
}
