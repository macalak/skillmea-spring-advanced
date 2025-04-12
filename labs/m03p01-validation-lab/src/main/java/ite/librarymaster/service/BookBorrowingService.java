package ite.librarymaster.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.UUID;

@Service
public class BookBorrowingService {
    private final Logger LOG = LoggerFactory.getLogger(BookBorrowingService.class);

    public void recordBorrowingDates(UUID borrowingId,
                                     Date borrowingDate,
                                     Date returnDate){
        LOG.info("Dates were updated for Borrowing with ID: {}",borrowingId);
    }
}
