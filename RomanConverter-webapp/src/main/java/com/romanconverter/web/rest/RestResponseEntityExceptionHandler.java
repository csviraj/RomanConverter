package com.romanconverter.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * We use Controller Advice to consolidate and handle exception trown by the app here. For this
     * @param ex is the exception that if thrown by the application will be handled here.
     * @param request
     * @return Return the ResponseEntity populated with error message and appropriate Http error status.
     */
    @ExceptionHandler(value = { IllegalArgumentException.class, RuntimeException.class,NumberFormatException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        if(ex instanceof IllegalArgumentException || ex instanceof NumberFormatException) {
            return new ResponseEntity<>("BAD REQUEST, ERROR:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("ERROR:" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
