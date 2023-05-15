package com.puresushi.cse364project.exception;

import com.puresushi.cse364project.data.AttendanceUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AttendanceUserNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AttendanceUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String attendanceUserNotFoundHandler(AttendanceUserNotFoundException exception) {return exception.getMessage();}
}
