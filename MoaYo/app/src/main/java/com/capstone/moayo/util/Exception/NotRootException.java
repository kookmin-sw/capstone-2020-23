package com.capstone.moayo.util.Exception;

public class NotRootException extends RuntimeException {

    private static final long serialVersionUID = -973903075046111531L;

    public NotRootException(String message) {
        super(message);
    }
}
