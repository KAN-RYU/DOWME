package com.puresushi.cse364project.exception;

public class BusIdNotExistException extends RuntimeException{
    public BusIdNotExistException(String busId) { super("BusId is not existed: "+busId);}
}
