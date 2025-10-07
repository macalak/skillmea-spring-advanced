package ite.librarymaster.validation;

import java.util.Objects;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ite.librarymaster.model.PasswordHolder;


public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordHolder.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordHolder ph = (PasswordHolder) target;
        ValidationUtils.rejectIfEmpty(errors, "passwordConfirmation", "passwordConfirmation.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
        if (!Objects.equals(ph.getPassword(), ph.getPasswordConfirmation())) {
            errors.rejectValue("passwordConfirmation",
                "passwordConfirmation.dontmatch",
                "password and confirm password must be same.");
        }
    }
}
