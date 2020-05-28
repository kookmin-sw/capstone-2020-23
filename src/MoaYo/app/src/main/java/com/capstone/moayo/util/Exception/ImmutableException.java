package com.capstone.moayo.util.Exception;

public class ImmutableException extends RuntimeException {
    private static final long serialVersionUID = -5897585624620490608L;

    public ImmutableException(String message) {
        super(message);
    }
}
