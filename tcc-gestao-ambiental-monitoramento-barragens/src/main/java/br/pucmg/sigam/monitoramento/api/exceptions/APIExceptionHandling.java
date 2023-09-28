package br.pucmg.sigam.monitoramento.api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class APIExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIError> handleEntityNotFoundException(EntityNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        APIError apiError = APIError.builder()
                .error(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .message(exception.getLocalizedMessage())
                .build();

        return new ResponseEntity<APIError>(apiError, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldError> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(FieldError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build());
        });

        APIErrorValidation apiErrorValidation = new APIErrorValidation(
                status.value(),
                LocalDateTime.now(),
                "Houveram erros de validação",
                errors
        );

        return new ResponseEntity<>(apiErrorValidation, status);
    }
}