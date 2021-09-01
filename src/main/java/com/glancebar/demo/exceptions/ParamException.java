package com.glancebar.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author YISHEN CAI
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParamException extends RuntimeException {

    public ParamException(String message) {
        super(message);
    }
}
