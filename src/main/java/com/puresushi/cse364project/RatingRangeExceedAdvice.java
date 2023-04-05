package com.puresushi.cse364project;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RatingRangeExceedAdvice {
    @ResponseBody
    @ExceptionHandler(RatingRangeExceedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ratingRangeExceedHandler(RatingRangeExceedException exception) {
        return exception.getMessage();
    }
}
