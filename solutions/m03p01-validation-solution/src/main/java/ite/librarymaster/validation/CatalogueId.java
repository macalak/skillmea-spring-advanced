package ite.librarymaster.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CatalogueIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CatalogueId {
    String message() default "Invalid Catalogue ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

