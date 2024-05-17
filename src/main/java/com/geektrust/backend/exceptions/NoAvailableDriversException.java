package com.geektrust.backend.exceptions;

public class NoAvailableDriversException extends RuntimeException {
    public NoAvailableDriversException(String message) {
        super(message);
    }

    public NoAvailableDriversException(String message, Throwable cause) {
        super(message, cause);
    }
}
