package com.puresushi.cse364project;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String id) {
        super("Could not find employee id=" + id);
    }
}
