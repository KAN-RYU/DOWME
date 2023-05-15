package com.puresushi.cse364project.exception;

public class AttendanceUserNotFoundException extends RuntimeException{
    public AttendanceUserNotFoundException(Long id) { super("User id not found: "+id);}
}
