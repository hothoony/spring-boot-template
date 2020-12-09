package study.springbootbasic.global.error.exception;

import org.springframework.validation.Errors;

public class BeanValidationException extends RuntimeException {

    private Errors errors;

    public BeanValidationException() {
        super();
    }

    public BeanValidationException(String message) {
        super(message);
    }

    public BeanValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanValidationException(Throwable cause) {
        super(cause);
    }

    public BeanValidationException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
