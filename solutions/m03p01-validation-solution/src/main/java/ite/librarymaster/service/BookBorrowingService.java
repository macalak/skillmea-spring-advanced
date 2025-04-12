package ite.librarymaster.service;

import ite.librarymaster.validation.ConsistentDateParameters;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.UUID;

@Service
@Validated
public class BookBorrowingService {
    private final Logger LOG = LoggerFactory.getLogger(BookBorrowingService.class);

    //@ConsistentDateParameters
    @ParameterScriptAssert(script = "borrowingDate.before(returnDate)", lang = "groovy")
    public void recordBorrowingDates(UUID borrowingId,
                                     @NotNull Date borrowingDate,
                                     @NotNull Date returnDate){
        LOG.info("Dates were updated for Borrowing with ID: {}",borrowingId);
    }
}
