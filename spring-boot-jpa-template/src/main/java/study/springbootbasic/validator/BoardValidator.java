package study.springbootbasic.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import study.springbootbasic.dto.BoardDto;
import study.springbootbasic.dto.BoardDto2;

import static study.springbootbasic.validator.ValidatorUtil.assertNotNullOrRejectValue;

@Component
public class BoardValidator {

    public void validate(BoardDto boardDto, BindingResult errors) {
//        notNull(boardDto, "boardDto should not be null");
//        notNull(boardDto.getStartDate(), "startDate should not be null");

        errors.rejectValue("startDate", "error.null", "validate fail, BoardValidator 1");
    }

    public void validate(BoardDto2 boardDto, BindingResult errors) {
//        notNull(boardDto, "boardDto should not be null");
//        notNull(boardDto.getStartDate(), "startDate should not be null");

        assertNotNullOrRejectValue(boardDto.getStartDate(), errors, "startDate");

        errors.rejectValue("startDate", "error.null", "validate fail, BoardValidator 2");
    }

}
