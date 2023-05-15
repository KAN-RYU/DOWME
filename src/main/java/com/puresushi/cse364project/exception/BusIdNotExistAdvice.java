package com.puresushi.cse364project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BusIdNotExistAdvice {
    @ResponseBody
    @ExceptionHandler(BusIdNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String busIdNotExistHandler(BusIdNotExistException exception) { return exception.getMessage();}
}
