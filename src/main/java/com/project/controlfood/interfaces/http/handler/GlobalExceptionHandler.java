package com.project.controlfood.interfaces.http.handler;

import com.project.controlfood.domain.exception.InternalErrorException;
import com.project.controlfood.interfaces.http.dto.ExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ExceptionDetails> handleInternalErrorException(InternalErrorException exception) {
        ExceptionDetails validationExceptionDetails = new ExceptionDetails();
        validationExceptionDetails.setMessage(exception.getMessage());
        validationExceptionDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        validationExceptionDetails.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(validationExceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Fields Error {}", methodArgumentNotValidException.getAllErrors());
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        ExceptionDetails validationExceptionDetails = new ExceptionDetails();
        validationExceptionDetails.setMessage("Check the wrong fields");
        validationExceptionDetails.setStatus(HttpStatus.BAD_REQUEST);
        validationExceptionDetails.setTimestamp(LocalDateTime.now());
        validationExceptionDetails.setViolations(getViolations(fieldErrors));
        return new ResponseEntity<>(validationExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getViolations(List<FieldError> fieldErrors) {
        Map<String, String> violations = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            violations.put(field, message);
        }
        return violations;
    }

}
