package ua.com.owu.valid;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.owu.entity.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "label.validate.emailEmpty");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "label.validate.passwordEmpty");
    }

}