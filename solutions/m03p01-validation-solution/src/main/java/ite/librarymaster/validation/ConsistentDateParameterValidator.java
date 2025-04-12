package ite.librarymaster.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

import java.time.Instant;
import java.util.Date;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator
        implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public boolean isValid( Object[] value, ConstraintValidatorContext context) {
        Date startDate = (Date) value[1];
        Date endDate =  (Date) value[2];
        Instant now = Instant.now();
        return endDate.toInstant().isAfter(now) && startDate.toInstant().isAfter(now) &&
               endDate.toInstant().isAfter(startDate.toInstant());
    }
}
