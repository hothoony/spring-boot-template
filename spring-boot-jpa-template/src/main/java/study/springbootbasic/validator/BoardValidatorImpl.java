package study.springbootbasic.validator;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import study.springbootbasic.dto.BoardDto;

@Component
@RequiredArgsConstructor
public class BoardValidatorImpl implements Validator {

    private final SpringValidatorAdapter validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        notNull(boardDto, "boardDto should not be null");
//        notNull(boardDto.getStartDate(), "startDate should not be null");

        errors.rejectValue("startDate", "error.null", "validate fail, BoardValidatorImpl");
    }
}
