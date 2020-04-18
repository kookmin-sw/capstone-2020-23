package com.capstone.moayo.util.Exception;

public class NotRootException extends Exception {
    public NotRootException() {
        super("Reference is not root.");
    }
}
