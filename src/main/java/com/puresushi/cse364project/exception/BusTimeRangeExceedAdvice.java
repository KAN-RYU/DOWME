package com.puresushi.cse364project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class BusTimeRangeExceedAdvice {
    @ResponseBody
    @ExceptionHandler(BusTimeRangeExceedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bustTimeRangeExceedHandler(BusTimeRangeExceedException exception) { return exception.getMessage();}
}
