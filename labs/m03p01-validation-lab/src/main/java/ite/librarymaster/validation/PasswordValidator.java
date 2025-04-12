package ite.librarymaster.validation;

import ite.librarymaster.model.PasswordHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;


public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordHolder.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordHolder ph = (PasswordHolder) target;
        ValidationUtils.rejectIfEmpty(errors, "passwordConfirmation", "passwordConfirmation.empty","DEFAULT");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty", "DEFAULT");
        if (!Objects.equals(ph.getPassword(), ph.getPasswordConfirmation())) {
            errors.rejectValue("passwordConfirmation",
                "passwordConfirmation.dontmatch",
                "DEFAULT: password and confirm password must be same.");
        }
    }
}
