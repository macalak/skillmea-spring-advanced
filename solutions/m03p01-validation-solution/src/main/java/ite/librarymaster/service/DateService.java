package ite.librarymaster.service;

import ite.librarymaster.validation.ConsistentDateParameters;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Component
@Validated
public class DateService {

    @ConsistentDateParameters
    public void someFunction(
            String title,
            @NotNull Date startDate,
            @NotNull Date endDate) {
        System.out.println();
    }
}
