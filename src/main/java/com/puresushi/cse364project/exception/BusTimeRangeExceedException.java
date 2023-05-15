package com.puresushi.cse364project.exception;


public class BusTimeRangeExceedException extends RuntimeException{
    public BusTimeRangeExceedException(int time) { super("Time is not in range: "+time);}
}
