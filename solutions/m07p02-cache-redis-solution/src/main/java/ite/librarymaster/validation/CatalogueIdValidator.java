package ite.librarymaster.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CatalogueIdValidator implements ConstraintValidator<CatalogueId, String> {
    //LM-000001
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^LM-[0-9]+")
                && (value.length() >= 8) && (value.length() <= 13);
    }
}
