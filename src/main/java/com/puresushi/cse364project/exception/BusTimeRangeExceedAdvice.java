package com.puresushi.cse364project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class BusTimeRangeExceedAdvice {
    @ResponseBody
    @ExceptionHandler(BusTimeRangeExceedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String bustTimeRangeExceedHandler(BusTimeRangeExceedException exception) { return exception.getMessage();}
}
