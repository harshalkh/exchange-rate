package com.harshal.currency.exchange.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExchangeRestServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = { Exception.class })
    protected ResponseEntity<Object> handleError(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Something went wrong!!! Please try again later.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}