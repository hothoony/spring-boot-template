package study.springbootbasic.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String LN = "\n";
    private static final String LN_TAB = "\n    ";

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildBadRequest(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildBadRequest("invalid request body");
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildBadRequest("unavailable request");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Object body = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(o -> o.getField() + ": " + o.getDefaultMessage())
                .collect(Collectors.toList());
        return buildBadRequest(body);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return buildBadRequest("invalid request");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> list = ex.getConstraintViolations()
                .stream()
                .map(o -> o.getRootBeanClass().getSimpleName() + "." + o.getPropertyPath() + ": " + o.getMessage())
                .collect(Collectors.toList());
        log.warn(ex.getClass().getName() + " " + list);
        Object body = ex.getConstraintViolations()
                .stream()
                .map(o -> getField(String.valueOf(o.getPropertyPath())) + ": " + o.getMessage())
                .collect(Collectors.toList());
        return buildBadRequest(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, "resource not found");
    }

    @ExceptionHandler(BeanValidationException.class)
    public ResponseEntity<Object> handleValidationException(BeanValidationException ex) {
        Object body = ex.getErrors()
                .getFieldErrors()
                .stream()
                .map(o -> o.getField() + ": " + o.getDefaultMessage())
                .collect(Collectors.toList());
        return buildBadRequest("validation failed " + body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        String message = getSimpleStackTraceMessage(ex);
        log.error(message, ex);
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<Object> buildBadRequest(Object message) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, message);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Object body) {
        ApiError apiError = new ApiError(httpStatus, body);
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    private String getSimpleStackTraceMessage(Exception ex) {
        String LN = "\n";
        String TAB = "    ";
        String SPACE = TAB;
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getClass().getName() + ": " + ex.getMessage());
        while (ex != null) {
            sb.append(LN + SPACE + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            sb.append(LN + SPACE + ex.getStackTrace()[0]);
            ex = (Exception) ex.getCause();
            SPACE += TAB;
        }
        return sb.toString();
    }

    private String getField(String str) {
        Optional<String> optional = Optional.ofNullable(str);
        if (optional.isEmpty()) return null;
        String[] split = str.split("\\.");
        return split[split.length - 1];
    }

    /*
        HttpMediaTypeNotSupportedException
            Content-Type 이 없거나 잘못된 경우 경우
        HttpMessageNotReadableException
            JSON 형식이 유효하지 않은 경우
        MethodArgumentTypeMismatchException
            PathVariable 타입이 맞지 않는 경우
        MethodArgumentNotValidException
            controller 에서 파라미터가 @Valid validation 을 통과하지 못한 경우
        ConstraintViolationException
            bean 에서 파라미터가 @Valid validation 을 통과하지 못한 경우

        UnexpectedTypeException
            validation annotation 을 잘못된 타입에 적은 경우
            @NotNull Long id (O)
            @NotEmpty Long id (X)
            @NotBlank Long id (X)

        Custom
            ResourceNotFoundException
     */
}
