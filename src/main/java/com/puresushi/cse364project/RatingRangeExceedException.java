package com.puresushi.cse364project;

public class RatingRangeExceedException extends RuntimeException{
    public RatingRangeExceedException(int rating) {
        super("Rating " + rating + " is not in range. (1-5)");
    }
}
