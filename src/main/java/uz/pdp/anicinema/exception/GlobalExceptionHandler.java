package uz.pdp.anicinema.exception;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.pdp.anicinema.utils.contant.MessageKey;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNullElse;
import static org.springframework.http.ResponseEntity.badRequest;
import static uz.pdp.anicinema.payload.response.ResponseData.errorResponseData;
import static uz.pdp.anicinema.utils.contant.MessageKey.PARAMETERS_NOT_VALID;
import static uz.pdp.anicinema.utils.enums.Code.INVALID_DATA;
import static uz.pdp.anicinema.utils.enums.Code.UNEXPECTED_ERROR;

@NonNullApi
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message = isNull(ex.getBindingResult().getFieldError()) ? PARAMETERS_NOT_VALID : ex.getBindingResult().getFieldError().getDefaultMessage();

        return badRequest().body(errorResponseData(INVALID_DATA, message));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return badRequest().body(errorResponseData(INVALID_DATA, PARAMETERS_NOT_VALID));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        return badRequest().body(errorResponseData(requireNonNullElse(ex.getCode(), UNEXPECTED_ERROR), ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(final BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageKey.PASSWORD_NOT_VALID);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(final AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
