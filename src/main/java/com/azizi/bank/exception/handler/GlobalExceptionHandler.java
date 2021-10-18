package com.azizi.bank.exception.handler;

import com.azizi.bank.exception.InvalidStateException;
import com.azizi.bank.exception.NotFoundException;
import com.azizi.bank.exception.model.Error;
import com.azizi.bank.exception.model.ValidationError;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<Object> handleInvalidState(InvalidStateException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ValidationError> validationErrors = getValidationErrors(ex.getBindingResult());
        return handleException(ex, validationErrors, HttpStatus.BAD_REQUEST);
    }

    private List<ValidationError> getValidationErrors(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    private ResponseEntity<Object> handleException(Exception ex, HttpStatus httpStatus) {
        return handleException(ex, null, httpStatus);
    }

    private ResponseEntity<Object> handleException(Exception ex, List<ValidationError> validationErrors,
                                                   HttpStatus httpStatus) {
        Error error = new Error(httpStatus.value(), ex.getMessage(), validationErrors);
        return new ResponseEntity<>(error, httpStatus);
    }


}
