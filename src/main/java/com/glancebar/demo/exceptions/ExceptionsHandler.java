package com.glancebar.demo.exceptions;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller layer error handling.
 *
 * @author YISHEN CAI
 */
@RestControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        Throwable rootCause = ((NestedRuntimeException) e).getRootCause();
        // TODO: 2021/8/28 handle null gracefully
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rootCause != null ? rootCause.getMessage() : null);
    }


    @ExceptionHandler(value = ParamException.class)
    public ResponseEntity<?> handleParamException(ParamException e) {
        e.getClass().getAnnotation(ResponseStatus.class).value();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<?> handleParamNotValidException(BindException e) {
        List<String> messages = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> {
            messages.add("Field: " + fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        if (messages.size() == 0) {
            e.getAllErrors().forEach(objectError -> messages.add("Param error: " + objectError.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(",", messages));
    }
}
