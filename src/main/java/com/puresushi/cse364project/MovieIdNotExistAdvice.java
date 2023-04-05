package com.puresushi.cse364project;

import com.puresushi.cse364project.data.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MovieIdNotExistAdvice {
    @ResponseBody
    @ExceptionHandler(MovieIdNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String movieIdNotExistHandler(MovieIdNotExistException exception) {
        return exception.getMessage();
    }
}
