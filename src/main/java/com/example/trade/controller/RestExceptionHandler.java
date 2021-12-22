package com.example.trade.controller;

import com.example.trade.error.BidException;
import com.example.trade.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgument(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        log.warn("Failed request: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse(1, ex.getMessage());

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = BidException.class)
    protected ResponseEntity<Object> handleBidException(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        log.warn("Failed request: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse(2, ex.getMessage());

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
