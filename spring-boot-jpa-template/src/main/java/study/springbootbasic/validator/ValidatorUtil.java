package study.springbootbasic.validator;

import org.springframework.validation.BindingResult;

import java.util.Optional;

public class ValidatorUtil {

    public static void assertNotNullOrRejectValue(Object property, BindingResult errors, String field) {
        assertNotNullOrRejectValue(property, errors, field, "error.null", "must not be null");
    }

    public static void assertNotNullOrRejectValue(Object property, BindingResult errors, String field, String errorCode, String defaultMessage) {
        if (Optional.ofNullable(property).isEmpty()) {
            errors.rejectValue(field, errorCode, defaultMessage);
        }
    }
}
