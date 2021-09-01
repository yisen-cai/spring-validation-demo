package com.glancebar.demo.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author YISHEN CAI
 */
@RestController
@RequestMapping("/error")
public class GlobalErrorController extends AbstractErrorController {


    /**
     * @param errorAttributes Provides access to error attributes which can be logged or presented to the user.
     */
    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }


    @RequestMapping
    public ResponseEntity<?> handleGlobalError(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return ResponseEntity.status(status).build();
        }
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        return ResponseEntity.status(status).body(body);
    }


    protected ErrorAttributeOptions getErrorAttributeOptions(HttpServletRequest request, MediaType mediaType) {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        options = options.including(ErrorAttributeOptions.Include.EXCEPTION);
        return options;
    }
}
